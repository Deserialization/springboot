1.kafka为啥会这么快

    写：为了提高读写硬盘的速度，Kafka就是使用顺序I/O
        磁盘顺序读写速度超过内存随机读写
        JVM的GC效率低，内存占用大。使用磁盘可以避免这一问题
        系统冷启动后，磁盘缓存依然可用
        
    读：sendfile系统调用，文件数据被copy至内核缓冲区
        再从内核缓冲区copy至内核中socket相关的缓冲区
        最后再socket相关的缓冲区copy到协议引擎
	
2.Spring 框架中用到了哪些设计模式：

	工厂设计模式 : Spring使用工厂模式通过 BeanFactory、ApplicationContext 创建 bean 对象。
	代理设计模式 : Spring AOP 功能的实现。
	单例设计模式 : Spring 中的 Bean 默认都是单例的。
	模板方法模式 : Spring 中 jdbcTemplate、hibernateTemplate 等以 Template 结尾的对数据库操作的类，它们就使用到了模板模式。
	包装器设计模式 : 我们的项目需要连接多个数据库，而且不同的客户在每次访问中根据需要会去访问不同的数据库。这种模式让我们可以根据客户的需求能够动态切换不同的数据源。
	观察者模式: Spring 事件驱动模型就是观察者模式很经典的一个应用。
	适配器模式 :Spring AOP 的增强或通知(Advice)使用到了适配器模式、spring MVC 中也是用到了适配器模式适配

3.单例：	

	1. 饿汉模式
	public class Singleton {  
		 private static Singleton instance = new Singleton();  
		 private Singleton (){
		 }
		 public static Singleton getInstance() {  
		 return instance;  
		 }  
	 }  
	 
	这种方式在类加载时就完成了初始化，所以类加载较慢，但获取对象的速度快。 这种方式基于类加载机制避免了多线程的同步问题，但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到懒加载的效果。

	2. 懒汉模式（线程不安全）
	public class Singleton {  
		  private static Singleton instance;  
		  private Singleton (){
		  }   
		  public static Singleton getInstance() {  
		  if (instance == null) {  
			  instance = new Singleton();  
		  }  
		  return instance;  
		  }  
	 }  
	 

	懒汉模式申明了一个静态对象，在用户第一次调用时初始化，虽然节约了资源，但第一次加载时需要实例化，反映稍慢一些，而且在多线程不能正常工作。

	3. 懒汉模式（线程安全）

	public class Singleton {  
		  private static Singleton instance;  
		  private Singleton (){
		  }
		  public static synchronized Singleton getInstance() {  
		  if (instance == null) {  
			  instance = new Singleton();  
		  }  
		  return instance;  
		  }  
	 }  
	 

	这种写法能够在多线程中很好的工作，但是每次调用getInstance方法时都需要进行同步，造成不必要的同步开销，而且大部分时候我们是用不到同步的，所以不建议用这种模式。

	4. 双重检查模式 （DCL）

	public class Singleton {  
		  private volatile static Singleton singleton;  
		  private Singleton (){
		  }   
		  public static Singleton getInstance() {  
		  if (instance== null) {  
			  synchronized (Singleton.class) {  
			  if (instance== null) {  
				  instance= new Singleton();  
			  }  
			 }  
		 }  
		 return singleton;  
		 }  
	 }  
	 

	这种写法在getSingleton方法中对singleton进行了两次判空，第一次是为了不必要的同步，第二次是在singleton等于null的情况下才创建实例。在这里用到了volatile关键字，不了解volatile关键字的可以查看Java多线程（三）volatile域这篇文章，在这篇文章我也提到了双重检查模式是正确使用volatile关键字的场景之一。 
	在这里使用volatile会或多或少的影响性能，但考虑到程序的正确性，牺牲这点性能还是值得的。 DCL优点是资源利用率高，第一次执行getInstance时单例对象才被实例化，效率高。缺点是第一次加载时反应稍慢一些，在高并发环境下也有一定的缺陷，虽然发生的概率很小。DCL虽然在一定程度解决了资源的消耗和多余的同步，线程安全等问题，但是他还是在某些情况会出现失效的问题，也就是DCL失效，在《Java并发编程实践》一书建议用静态内部类单例模式来替代DCL。

	5. 静态内部类单例模式

	public class Singleton { 
		private Singleton(){
		}
		  public static Singleton getInstance(){  
			return SingletonHolder.sInstance;  
		}  
		private static class SingletonHolder {  
			private static final Singleton sInstance = new Singleton();  
		}  
	} 
	第一次加载Singleton类时并不会初始化sInstance，只有第一次调用getInstance方法时虚拟机加载SingletonHolder 并初始化sInstance ，这样不仅能确保线程安全也能保证Singleton类的唯一性，所以推荐使用静态内部类单例模式。

	6. 枚举单例

	public enum Singleton {  
		 INSTANCE;  
		 public void doSomeThing() {  
		 }  
	 }  
	 

	默认枚举实例的创建是线程安全的，并且在任何情况下都是单例，上述讲的几种单例模式实现中，有一种情况下他们会重新创建对象，那就是反序列化，将一个单例实例对象写到磁盘再读回来，从而获得了一个实例。反序列化操作提供了readResolve方法，这个方法可以让开发人员控制对象的反序列化。在上述的几个方法示例中如果要杜绝单例对象被反序列化是重新生成对象，就必须加入如下方法：

	private Object readResolve() throws ObjectStreamException{
	return singleton;
	}
	枚举单例的优点就是简单，但是大部分应用开发很少用枚举，可读性并不是很高，不建议用。

	7. 使用容器实现单例模式

	public class SingletonManager { 
	　　private static Map<String, Object> objMap = new HashMap<String,Object>();
	　　private Singleton() { 
	　　}
	　　public static void registerService(String key, Objectinstance) {
	　　　　if (!objMap.containsKey(key) ) {
	　　　　　　objMap.put(key, instance) ;
	　　　　}
	　　}
	　　public static ObjectgetService(String key) {
	　　　　return objMap.get(key) ;
	　　}
	}
	用SingletonManager 将多种的单例类统一管理，在使用时根据key获取对象对应类型的对象。这种方式使得我们可以管理多种类型的单例，并且在使用时可以通过统一的接口进行获取操作，降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度。

	总结
	到这里七中写法都介绍完了，至于选择用哪种形式的单例模式，取决于你的项目本身，是否是有复杂的并发环境，还是需要控制单例对象的资源消耗。
	
静态代理类与动态代理

    1.静态代理类：
    由程序员创建或由特定工具自动生成源代码，再bai对其编译。在程序运行前，代理类的.class文件就已经存在了。动态代理类：在程序运行时，运用反射机制动态创建而成
    2.动态代理类
    与静态代理类对照的是动态代理类，动态代理类的字节码在程序运行时由Java反射机制动态生成，无需程序员手工编写它的源代码。动态代理类不仅简化了编程工作，而且提高了软件系统的可扩展性，因为Java 反射机制可以生成任意类型的动态代理类。java.lang.reflect 包中的Proxy类和InvocationHandler 接口提供了生成动态代理类的能力。

zk实现分布式锁：

同步异步 阻塞 非阻塞区别

    BIO:同步阻塞;一个线程对应一个socket
    NIO:同步非阻塞：有个selector（选择器：多路复用，监听多个通道）：一个线程有多个socket；
    AIO;异步非阻塞：异步通道：

    B阻塞：当前线程暂时没有数据可读的时候、accept或者是read的时候。
    N阻塞：thread-->channel-->buffer-->socket. thread不会阻塞
      -->channel-->buffer-->socket.
    buffer（内存块）:读写都可以用flip切换，双向的，除了boolean类型都有。
    channel是双向的
    selector对应一个线程，会根据不同的事件，切换不同的通道，一个线程对应多个channel，
    每个channel对应一个buffer.
    selector:能够检测多个注册的通道是否有事件发生。单线程，多路复用，调用select方法，select方法可以获取到注册上去的selectionkey，遍历selectionkey获取到channel。
    selector.select().//阻塞；
    
    当客户端连接时，会通过ServerSocketChannel 得到 SocketChannel
    将socketChannel注册到Selector上, register(Selector sel, int ops), 一个selector上可以注册多个SocketChannel
    注册后返回一个 SelectionKey, 会和该Selector 关联(集合)
    Selector 进行监听  select 方法, 返回有事件发生的通道的个数.
    进一步得到各个 SelectionKey (有事件发生)
    在通过 SelectionKey  反向获取 SocketChannel , 方法 channel()
    可以通过  得到的 channel  , 完成业务处理
    代码撑腰。。。


单 Reactor 单线程；
单 Reactor 多线程；
主从 Reactor 多线程：
 
    Netty 线程模式(Netty 主要基于主从 Reactor 多线程模型做了一定的改进，其中主从 Reactor 多线程模型有多个 Reactor) 
    I/O 复用结合线程池，就是 Reactor 模式基本设计思想，
    Reactor 模式，通过一个或多个输入同时传递给服务处理器的模式(基于事件驱动)
    服务器端程序处理传入的多个请求,并将它们同步分派到相应的处理线程， 因此Reactor模式也叫 Dispatcher模式
    Reactor 模式使用IO复用监听事件, 收到事件后，分发给某个线程(进程), 这点就是网络服务器高并发处理关键

