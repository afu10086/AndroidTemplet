# AndroidTemplet
Android常规项目框架，采用MVP架构,pull后可以在此基础上直接开发，节省开发时间

* 1 项目网络请求采用okhttp框架并进行了封装,已经集成了一些常用的第三方库;
项目中采用MVP架构,并用LoginActivity做了一个事例,其它的类开发可以参照LoginActivity
类的方式进行开发,当遇到类似的项目时可以直接pull下稍微修改下就可以在些基础上进行开发


* 1.1 项目架构图:

  ![image](https://s3.amazonaws.com/f.cl.ly/items/0n1F473O2Z1H060n0x1l/androidTemplet架构.png)

* 1.2 项目运行事例

  ![gif](https://s3.amazonaws.com/f.cl.ly/items/3v1v3y1R3q0f0V263c1B/android.gif)


#### Android开发规范

#####编码规范
* 1 开发android的workspace建议编码用UTF-8	
* 2.开发时jave类以及各种文件建议编码用UTF-8
* 3.java代码中建议不要出现中文，最多注释中可以出现中文,但建议最好都不用中文

#####命名规范
* 1.局部变量命名、静态成员变量命名:
只能包含字母，单词首字母出第一个外，都为大写，其他字母都为小写
* 2.常量命名:只能包含字母和_，字母全部大写，单词之间用_隔开. 例如:public static final String CODE_HTTP_RESTART_CLIENT = "4000";
* 3.layout中的id命名:
* 3.1 命名模式为：view缩写_模块名称_view的逻辑名称
view的缩写详情如下
LayoutView：lv RelativeView:rv TextView:tv ImageView:iv ImageButton:im Button:btn 5、activity中的view变量命名
* 3.2 命名模式为：逻辑名称+view缩写
建议：如果layout文件很复杂，建议将layout分成多个模块，每个模块定义一个moduleViewHolder，其成员变量包含所属view
* 4.strings.xml中的id命名：activity名称_功能模块名称_逻辑名称
* 5.drawable中的图片命名：activity名称_逻辑名称或common_逻辑名称

#####使用资源时的规范
* 1.styles.xml：将layout中不断重现的style提炼出通用的style通用组件，放到styles.xml中；
* 2.使用layer-list和selector
* 3.图片尽量分拆成多个可重用的图片
* 4.服务端可以实现的，就不要放在客户端
* 5.引用第三方库要慎重，避免应用大容量的第三方库，导致客户端包非常大
* 6.strings.xml中使用%1$s实现字符串的通配

#####Android开发注意事项
* 1.使用静态变量方式实现界面间共享要慎重
* 2.Log(系统名称模块名称接口名称，详细描述)
* 3.单元测试（逻辑测试、界面测试），开发完某个功能时最好先单元测试。
* 4.不要重用父类的handler，对应一个类的handler也不应该让其子类用到，否则会导致message.what冲突
* 5.如果多个Activity中包含共同的UI处理，那么可以提炼一个CommonActivity，把通用部分叫由它来处理，其他activity只要继承它即可
* 6.如果所开发的为通用组件，为避免冲突，将drawable/layout/menu/values目录下的文件名增加前缀
* 7.数据一定要效验，例如
字符型转数字型，如果转换失败一定要有缺省值；
* 8.数字、字母和汉字混排占位问题：将数字和字母全角化。由于现在大多数情况下我们的输入都是半角，所以字母和数字的占位无法确定，但是一旦全角化之后，数字、字母的占位就和一个汉字的占位相同了，这样就可以避免由于占位导致的排版问题
* 9.使用styles，复用样式定义










