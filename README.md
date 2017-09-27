# SUtilLibrary
![](s.png)![](j.png)![](y.png)

### 如何使用

#### Android Studio
    第一步：
      在项目的gradle里配置
      allprojects {
      		repositories {
      			...
      			maven { url 'https://jitpack.io' }
      		}
      	}

      第二步：
      在module的gradle里配置
      dependencies {
      	        compile 'com.github.shajinyang:SjyndriodLibrarys:1.0.7'
      	}

      	第三步：
      	在自己的application里初始化
      	Sjutil.init(this);

### 公共常用父类
####  BaseActivity
      支持自义定状态栏样式，透明状态栏，无状态栏
      支持databinding数据绑定
      使用方法（和官方databinding使用方法相同）：
      1，在gradle配置
      android{
      ...
           dataBinding {
              enabled true
          }

       ...
      }
      2,在对应的xml布局文件根节点添加<layout></layout>
      3,继成activity即可

#### BaseFragment
      支持懒加载
      支持databinding数据绑定
      使用方法同BaseActivity
      initData()  为懒加载方法
      init() 初始化加载








### android开发常用工具类

#### AppInfoUtil
     app信息工具类

#### CharacterParser
     字符工具类

#### ColorUtil
     颜色工具类

#### DateUtil
     日期工具类

#### DisplayUtil
      设备尺寸工具类

#### FileUtil
    文件帮助工具类

#### NumberUtil
      数字处理工具类

#### PicUtil
    图片工具类

#### SettingsUtil
     设置工具类

#### ViewUtil
    视图工具类


