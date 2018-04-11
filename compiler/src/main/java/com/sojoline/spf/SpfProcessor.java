package com.sojoline.spf;


import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import com.sojoline.annotation.SpfConfig;
import com.sojoline.annotation.SpfPreferences;
import com.sojoline.common.ErrorReporter;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/09/25
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@SupportedAnnotationTypes("com.sojoline.annotation.SpfPreferences")
@AutoService(Processor.class)
public class SpfProcessor extends AbstractProcessor{
	private ErrorReporter errorReporter;
	private Elements elementUtils;
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		errorReporter = new ErrorReporter(processingEnv);
		elementUtils = processingEnv.getElementUtils();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(SpfPreferences.class);
		ImmutableList<TypeElement> list = new ImmutableList.Builder<TypeElement>()
				.addAll(ElementFilter.typesIn(annotatedElements))
				.build();
		for (TypeElement type : list) {
			processType(type);
		}
		return false;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

	private void processType(TypeElement typeElement){
		SpfPreferences p = typeElement.getAnnotation(SpfPreferences.class);

		if (p == null) {
			errorReporter.abortWithError("annotation processor for @AutoParcel was invoked with a " +
					"type annotated differently; compiler bug? O_o", typeElement);
		}

		if (typeElement.getKind() != ElementKind.CLASS){
			errorReporter.abortWithError("@" + SpfPreferences.class.getName() + " only applies to classes", typeElement);
		}

//		获取该类的全部成员
		List<? extends Element> members = elementUtils.getAllMembers(typeElement);
		List<MethodSpec> methodSpecs = new ArrayList<>();
		Set<Element> inClassElements = new HashSet<>();

		for (Element item : members) {

			// 忽略除了成员方法外的元素
			if (!(item instanceof ExecutableElement)){
				continue;
			}

			//忽略final、static 方法
			if (item.getModifiers().contains(Modifier.FINAL) || item.getModifiers().contains(Modifier.STATIC)){
				continue;
			}

			//类的方法
			ExecutableElement executableElement = (ExecutableElement) item;
			String name = item.getSimpleName().toString();

			// 忽略基类的一个get方法
			if (name.contains("getClass")){
				continue;
			}

			// 忽略不是get、set、is 开头的方法
			boolean getter;
			if (name.startsWith("get") || name.startsWith("is")){
				getter = true;
			}else if (name.startsWith("set")){
				getter = false;
			}else {
				continue;
			}

			// 从方法名称提取成员变量的名称
			String fieldName = name.replaceFirst("get|is|set","");
			//成员变量名称（如：方法getUser 变量user）
			fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
			// 根据名称提取成员变量的元素
			Element fieldElement = getElement(members, fieldName);
			if (fieldElement == null) {
				continue;
			}

			//检查成员变量是否有SpfConfig注解
			SpfConfig annotation = fieldElement.getAnnotation(SpfConfig.class);
			//检查是否保存
			if (annotation != null && ! annotation.save()) {
				continue;
			}

			String modName;
			boolean isDouble = false;
			boolean isObject = false;
			//获取成员变量的类型
			TypeName typeName = TypeName.get(fieldElement.asType());
			if (typeName.equals(TypeName.BOOLEAN)){
				if (getter) {
					modName = "getBoolean";
				}else {
					modName = "putBoolean";
				}
			}else if (typeName.equals(TypeName.FLOAT)){
				if (getter) {
					modName = "getFloat";
				}else {
					modName = "putFloat";
				}
			}else if (typeName.equals(TypeName.INT)){
				if (getter) {
					modName = "getInt";
				}else {
					modName = "putInt";
				}
			}else if (typeName.equals(TypeName.DOUBLE)){
				if (getter) {
					modName = "getDouble";
				}else {
					modName = "putDouble";
				}
				isDouble = true;
			}else if (typeName.equals(TypeName.LONG)){
				if (getter) {
					modName = "getLong";
				}else {
					modName = "putLong";
				}
			}else {
				if (getter) {
					modName = "getString";
				}else {
					modName = "putString";
				}

				// 检查preferences是否登录true，如果是true代表这个对象作为preferences来保存，否则以对象持久化
				if (annotation != null && annotation.preferences()) {
					inClassElements.add(fieldElement);
					continue;
				}
				isObject = true;
			}

			boolean isGlobal = true;
			if (annotation != null && !annotation.global()) {
				isGlobal = false;
			}

			if (name.startsWith("set")){
				//生成所有set获取方法
				if (isObject) {
					MethodSpec setMethod = MethodSpec.overriding(executableElement)
							.addStatement(String.format("mEdit.putString(getRealKey(\"%s\",%b), AptPreferencesManager.getAptParser().serialize(%s)).apply()",
									fieldName, isGlobal, fieldName))
							.build();
					methodSpecs.add(setMethod);
					continue;
				}

				MethodSpec setMethod;
				String fieldNameModify = fieldName;
				// 加入字符串加密部分逻辑
				if (annotation != null && annotation.encryption()) {
					if (!typeName.equals(TypeName.get(String.class))){
						errorReporter.abortWithError("@" + SpfPreferences.class.getName() + " only string can be encrypted!", typeElement);
					}
					fieldNameModify = String.format("AptPreferencesManager.getSpfEncryption().encodeStr(%s)", fieldName);
				}

				if (annotation != null && annotation.commit()) {
					if (isDouble) {
						setMethod= MethodSpec.overriding(executableElement)
								.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), (float)%s).commit()",
										modName, fieldName, isGlobal, fieldName)).build();
					} else {
						setMethod = MethodSpec.overriding(executableElement)
								.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), %s).commit()",
										modName, fieldName, isGlobal, fieldNameModify)).build();
					}
				} else {
					if (isDouble) {
						setMethod = MethodSpec.overriding(executableElement)
								.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), (float)%s).apply()",
										modName, fieldName, isGlobal, fieldName)).build();
					} else {
						setMethod = MethodSpec.overriding(executableElement)
								.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), %s).apply()",
										modName, fieldName, isGlobal, fieldNameModify)).build();
					}
				}
				methodSpecs.add(setMethod);

			} else {
				// 生成所有get获取方法
				if (isObject) {
					TypeName className = ClassName.get(fieldElement.asType());
					MethodSpec setMethod = MethodSpec.overriding(executableElement)
							.addStatement(String.format("String text = mPreferences.getString(getRealKey(\"%s\",%b), null)", fieldName, isGlobal))
							.addStatement("Object object = null")
							.addCode("if (text != null){\n" +
									"   object = AptPreferencesManager.getAptParser().deserialize($T.class,text);\n" +
									"}\n" +
									"if (object != null){\n" +
									"   return ($T) object;\n" +
									"}\n", className, className)
							.addStatement(String.format("return super.%s()", executableElement.getSimpleName()))
							.build();
					methodSpecs.add(setMethod);
					continue;
				}

				String fieldNameModify = null;
				if (annotation != null && annotation.encryption()) {
					if (!typeName.equals(TypeName.get(String.class))) {
						errorReporter.abortWithError("@" + SpfPreferences.class.getName() + " only string can be encrypted!", typeElement);
					}
					fieldNameModify = String.format("AptPreferencesManager.getSpfEncryption().decodeStr(%s)", fieldName);
				}

				if (isDouble) {
					MethodSpec setMethod = MethodSpec.overriding(executableElement)
							.addStatement(String.format("return mPreferences.%s(getRealKey(\"%s\",%b), (float)super.%s())",
									modName, fieldName, isGlobal, name))
							.build();

					methodSpecs.add(setMethod);
				} else {
					MethodSpec setMethod;
					if (fieldNameModify == null) {
						setMethod = MethodSpec.overriding(executableElement)
								.addStatement(String.format("return mPreferences.%s(getRealKey(\"%s\",%b), super.%s())",
										modName, fieldName, isGlobal, name))
								.build();
					} else {
						setMethod = MethodSpec.overriding(executableElement)
								.addStatement(String.format("String %s = mPreferences.%s(getRealKey(\"%s\",%b), super.%s())",
										fieldName, modName, fieldName, isGlobal, name))
								.addCode(String.format("return %s;\n", fieldNameModify))
								.build();
					}

					methodSpecs.add(setMethod);
				}
			}
		}

		TypeName targetClassName = ClassName.get(getPackageName(typeElement), typeElement.getSimpleName() + "Preferences");
		MethodSpec getMethodSpec2 = MethodSpec.methodBuilder("get")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.returns(targetClassName)
				.addCode("if (sInstance == null){\n" +
						"   synchronized ($T.class){\n" +
						"      if (sInstance == null){\n" +
						"          sInstance = new $T();\n" +
						"      }\n" +
						"   }\n" +
						"}\n" +
						"return sInstance;\n", targetClassName, targetClassName)
				.build();

		MethodSpec clearMethodSpec = MethodSpec.methodBuilder("clear")
				.addModifiers(Modifier.PUBLIC)
				.returns(TypeName.VOID)
				.addStatement("mEdit.clear().commit()")
				.build();

		MethodSpec getRealKeyMethodSpec = MethodSpec.methodBuilder("getRealKey")
				.addModifiers(Modifier.PUBLIC)
				.returns(String.class)
				.addParameter(String.class, "key")
				.addParameter(TypeName.BOOLEAN, "global")
				.addStatement("return global ? key : AptPreferencesManager.getUserInfo() + key")
				.build();

		List<TypeSpec> typeSpecs = getInClassTypeSpec(inClassElements);
		MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
				.addModifiers(Modifier.PUBLIC)
				.addStatement("mPreferences = $T.getContext().getSharedPreferences($S, 0)",
						ClassName.get("com.sojoline.model.storage", "AptPreferencesManager"), typeElement.getSimpleName())
				.addStatement("mEdit = mPreferences.edit()");
		for (TypeSpec typeSpec : typeSpecs) {
			constructor.addStatement(String.format("this.set%s(new %s())", typeSpec.name.replace("Preferences", ""), typeSpec.name));
		}

		FieldSpec fieldSpec = FieldSpec.builder(targetClassName, "sInstance", Modifier.PRIVATE, Modifier.STATIC)
				.initializer("new $T()", targetClassName)
				.build();
		TypeSpec typeSpec = TypeSpec.classBuilder(typeElement.getSimpleName() + "Preferences")
				.superclass(TypeName.get(typeElement.asType()))
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethods(methodSpecs)
				.addMethod(getMethodSpec2)
				.addMethod(constructor.build())
				.addMethod(clearMethodSpec)
				.addMethod(getRealKeyMethodSpec)
				.addField(ClassName.get("android.content", "SharedPreferences", "Editor"), "mEdit", Modifier.PRIVATE, Modifier.FINAL)
				.addField(ClassName.get("android.content", "SharedPreferences"), "mPreferences", Modifier.PRIVATE, Modifier.FINAL)
				.addField(fieldSpec)
				.addTypes(typeSpecs)
				.build();
		JavaFile javaFile = JavaFile.builder(getPackageName(typeElement), typeSpec).build();

		try {
			javaFile.writeTo(processingEnv.getFiler());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<TypeSpec> getInClassTypeSpec(Set<Element> inClassElements) {
		List<TypeSpec> typeSpecs = new ArrayList<>();
		for (Element element : inClassElements) {
			TypeElement typeElement = elementUtils.getTypeElement(TypeName.get(element.asType()).toString());
			boolean globalField = true;
			SpfConfig tmp = element.getAnnotation(SpfConfig.class);
			if (tmp != null && !tmp.global()) {
				globalField = false;
			}
			List<? extends Element> members = elementUtils.getAllMembers(typeElement);
			List<MethodSpec> methodSpecs = new ArrayList<>();
			for (Element item : members) {

				if (item instanceof ExecutableElement) {
					ExecutableElement executableElement = (ExecutableElement) item;
					String name = item.getSimpleName().toString();
					if (name.equals("getClass")) {
						continue;
					}
					//忽略final、static 方法
					if (executableElement.getModifiers().contains(Modifier.FINAL) || executableElement.getModifiers().contains(Modifier.STATIC)) {
						continue;
					}
					if (!name.startsWith("get") && !name.startsWith("is") && !name.startsWith("set")) {
						continue;
					}
					String fieldName = name.replaceFirst("get|is|set", "");
					fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

					Element fieldElement = getElement(members, fieldName);
					SpfConfig annotation = item.getAnnotation(SpfConfig.class);
					if (annotation != null && !annotation.save()) {
						continue;
					}
					TypeName type = TypeName.get(fieldElement.asType());


					if (name.startsWith("set")) {
						String modName;
						boolean isDouble = false;
						if (type.equals(TypeName.BOOLEAN)) {
							modName = "putBoolean";
						} else if (type.equals(TypeName.INT)) {
							modName = "putInt";
						} else if (type.equals(TypeName.DOUBLE)) {
							modName = "putFloat";
							isDouble = true;
						} else if (type.equals(TypeName.FLOAT)) {
							modName = "putFloat";
						} else if (type.equals(TypeName.LONG)) {
							modName = "putLong";
						} else {
							modName = "putString";
						}
						MethodSpec setMethod;

						if (annotation != null && annotation.commit()) {
							if (isDouble) {
								setMethod = MethodSpec.overriding(executableElement)
										.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), (float)%s).commit()",
												modName, typeElement.getSimpleName() + "." + fieldName, globalField, fieldName)).build();
							} else {
								setMethod = MethodSpec.overriding(executableElement)
										.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), %s).commit()",
												modName, typeElement.getSimpleName() + "." + fieldName, globalField, fieldName)).build();
							}
						} else {
							if (isDouble) {
								setMethod = MethodSpec.overriding(executableElement)
										.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), (float)%s).apply()",
												modName, typeElement.getSimpleName() + "." + fieldName, globalField, fieldName)).build();
							} else {
								setMethod = MethodSpec.overriding(executableElement)
										.addStatement(String.format("mEdit.%s(getRealKey(\"%s\",%b), %s).apply()",
												modName, typeElement.getSimpleName() + "." + fieldName, globalField, fieldName)).build();
							}
						}
						methodSpecs.add(setMethod);

					} else if (name.startsWith("get") || name.startsWith("is")) {
						String modName;
						boolean isDouble = false;
						if (type.equals(TypeName.BOOLEAN)) {
							modName = "getBoolean";
						} else if (type.equals(TypeName.INT)) {
							modName = "getInt";
						} else if (type.equals(TypeName.DOUBLE)) {
							modName = "getFloat";
							isDouble = true;
						} else if (type.equals(TypeName.FLOAT)) {
							modName = "getFloat";
						} else if (type.equals(TypeName.LONG)) {
							modName = "getLong";
						} else {
							modName = "getString";
						}

						if (isDouble) {
							MethodSpec setMethod = MethodSpec.overriding(executableElement)
									.addStatement(String.format("return mPreferences.%s(getRealKey(\"%s\",%b), (float)super.%s())",
											modName, typeElement.getSimpleName() + "." + fieldName, globalField, name))
									.build();

							methodSpecs.add(setMethod);
						} else {
							MethodSpec setMethod = MethodSpec.overriding(executableElement)
									.addStatement(String.format("return mPreferences.%s(getRealKey(\"%s\",%b), super.%s())",
											modName, typeElement.getSimpleName() + "." + fieldName, globalField, name))
									.build();

							methodSpecs.add(setMethod);
						}

					}

				}
			}
			TypeSpec typeSpec = TypeSpec.classBuilder(typeElement.getSimpleName() + "Preferences")
					.superclass(TypeName.get(element.asType()))
					.addModifiers(Modifier.PRIVATE)
					.addMethods(methodSpecs)
					.build();
			typeSpecs.add(typeSpec);
		}
		return typeSpecs;
	}

	private Element getElement(List<? extends Element> members, String fieldName) {
		for (Element item : members) {
			if (item.getSimpleName().toString().equals(fieldName)) {
				return item;
			}
		}
		return null;
	}

	private String getPackageName(TypeElement type) {
		return elementUtils.getPackageOf(type).getQualifiedName().toString();
	}
}

