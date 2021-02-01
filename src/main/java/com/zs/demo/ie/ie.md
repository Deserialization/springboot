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
	
4.静态代理类与动态代理

    1.静态代理类：
    由程序员创建或由特定工具自动生成源代码，再bai对其编译。在程序运行前，代理类的.class文件就已经存在了。动态代理类：在程序运行时，运用反射机制动态创建而成
    2.动态代理类
    与静态代理类对照的是动态代理类，动态代理类的字节码在程序运行时由Java反射机制动态生成，无需程序员手工编写它的源代码。动态代理类不仅简化了编程工作，而且提高了软件系统的可扩展性，因为Java 反射机制可以生成任意类型的动态代理类。java.lang.reflect 包中的Proxy类和InvocationHandler 接口提供了生成动态代理类的能力。

5.zk实现分布式锁：

6.同步异步 阻塞 非阻塞区别
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


7.单Reactor 单线程/单 Reactor 多线程/主从 Reactor 多线程
    Netty 线程模式(Netty 主要基于主从 Reactor 多线程模型做了一定的改进，其中主从 Reactor 多线程模型有多个 Reactor) 
    I/O 复用结合线程池，就是 Reactor 模式基本设计思想，
    Reactor 模式，通过一个或多个输入同时传递给服务处理器的模式(基于事件驱动)
    服务器端程序处理传入的多个请求,并将它们同步分派到相应的处理线程， 因此Reactor模式也叫 Dispatcher模式
    Reactor 模式使用IO复用监听事件, 收到事件后，分发给某个线程(进程), 这点就是网络服务器高并发处理关键
    
   传统IO流：    
        采用阻塞IO模式获取输入的数据
        每个连接都需要独立的线程完成数据的输入，业务处理,数据返回
            问题分析
                当并发数很大，就会创建大量的线程，占用很大系统资源
                连接创建后，如果当前线程暂时没有数据可读，该线程会阻塞在read 操作，造成线程资源浪费
            解决（Reactor:I/O 复用结合线程池）：
                基于I/O复用模型：多个连接共用一个阻塞对象，应用程序只需要在一个阻塞对象等待，无需阻塞等待所有连接。当某个连接有新的数据可以处理时，操作系统通知应用程序，线程从阻塞状态返回，开始进行业务处理Reactor 对应的叫法: 1. 反应器模式 2. 分发者模式(Dispatcher) 3. 通知者模式(notifier)
                （Dispatcher转发）传统的Io没有dispatcher没有转发
                基于线程池复用线程资源：不必再为每个连接创建线程，将连接完成后的业务处理任务分配给线程进行处理，一个线程可以处理多个连接的业务。
                （线程重复利用）
   单Reactor 单线程:
         方案说明：         
             Select 是前面 I/O 复用模型介绍的标准网络编程 API，可以实现应用程序通过一个阻塞对象监听多路连接请求
             Reactor 对象通过 Select 监控客户端请求事件，收到事件后通过 Dispatch 进行分发
             如果是建立连接请求事件，则由 Acceptor 通过 Accept 处理连接请求，然后创建一个 Handler 对象处理连接完成后的后续业务处理
             如果不是建立连接事件，则 Reactor 会分发调用连接对应的 Handler 来响应
             Handler 会完成 Read→业务处理→Send 的完整业务流程
         方案优缺点分析：                  
             优点：模型简单，没有多线程、进程通信、竞争的问题，全部都在一个线程中完成
             缺点：性能问题，只有一个线程，无法完全发挥多核 CPU 的性能。Handler 在处理某个连接上的业务时，整个进程无法处理其他连接事件，很容易导致性能瓶颈
             缺点：可靠性问题，线程意外终止，或者进入死循环，会导致整个系统通信模块不可用，不能接收和处理外部消息，造成节点故障        
             使用场景：客户端的数量有限，业务处理非常快速，比如 Redis在业务处理的时间复杂度 O(1) 的情况                
   单Reactor多线程:
         方案说明
             Reactor 对象通过select 监控客户端请求事件, 收到事件后，通过dispatch进行分发
             如果建立连接请求, 则右Acceptor 通过accept 处理连接请求, 然后创建一个Handler对象处理完成连接后的各种事件
             如果不是连接请求，则由reactor分发调用连接对应的handler 来处理
             handler 只负责响应事件，不做具体的业务处理, 通过read 读取数据后，会分发给后面的worker线程池的某个线程处理业务
             worker 线程池会分配独立线程完成真正的业务，并将结果返回给handler
             handler收到响应后，通过send 将结果返回给client
   主从 Reactor 多线程：
    


