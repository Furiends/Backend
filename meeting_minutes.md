# Table of Contents

- May2022: [08 May](#date-08may2022)
- Apr 2022: [03 Apr](#date-03apr2022), [09 Apr](#date-09apr2022), [16 Apr](#date-16apr2022), [16 Apr会后](#date-16apr2022-wechat), [23 Apr](#date-23apr2022),[30 Apr](#date-30-apr2022)

# Date: 15May2022

- @Shaw:
- @锅巴：
- @Cherie：
- @小舒
- @Alice
    -   [黑马javaweb](https://www.bilibili.com/video/BV1Qf4y1T7Hx?spm_id_from=333.337.search-card.all.click) 复习了mysql， 学习了JDBC，入门了MyBatis
    -   进行了数据库表的构想，并与@Cherie讨论了两人的最终方案。

# Date: 08May2022

## 上周任务汇报

- @shaw（微信）
    - 复习mybatis和springboot的相关知识
    - 完成Litemall商城的小程序本地部署，阅读了部分源码进行学习
    - 进行了数据库表的设计构想

- @锅巴：
   - 调研了dependencies的配置，gradle的使用
   - 完成了TodoList的代码实现
   - 写技术文档

- cherie
  - 安装Intellij，学习配置
  - run锅巴的代码
  - 学习锅巴的技术文档，学习框架

- @小舒：
  - 学习了MyBatis，简化JDBC开发。
  - 启动一个SpringBoot程序，使用Maven配合SpringBoot
  - 用SpringBoot整合Junit和Mybatis，使用MyBatis连接数据库

- @Alice
    - 完成学习springboot quick start
    - maven + derby + JPA
    - postman发送API
    - CRUD 功能实现
    - 利用JPA实现数据库的entitiy relationship

## Discussion

- @锅巴：
   - 不清楚大家的进度，考虑是否需要前端的展示？建议大家直接练习Mybatis的操作。
   - 做最简化版的功能，如宠物信息的增删查改，先写一些简单的东西。
   - Maven和Graddle无偏好，都可以。
 
- cherie
  - MVP1流程图足够清晰，把流程图转换为需求，进行系统设计。考虑各种的UseCase，需要什么样的service。
  - 大公司的做法是由有经验的人进行high level的设计，事先确定好设计，再break成小任务。

- @小舒：
  - 建立google Drive存pdf类的资料
  - 不建议单独设计表，建议分组进行数据库设计

- @Alice
    - postman发送API，方便调试；前期可以用一个很简单的前端先熟悉，后面都是直接用API。
    - 先实现必须实现的功能，需要先在框架中就搭建起来。有些功能可以单独拆开打包成一个单独的service，后面再添加新的feature。
    - 需要跟前端进行讨论，考虑我们的技术进度，是否能赶上ddl
    - 大的系统设计可能不适合我们这种小团队，考虑先建数据库，之后进行功能迭代。

## 下周任务
- 数据库设计：alice和cherie一组，锅巴、小舒、shaw一组。
- 主持人应该确认好会议大纲，开会前大家需要直接把上一周完成的事项传入github，并且根据大纲事先准备发言，控制会议时间。
- 锅巴：写一个SpringBoot注解版的todolist。担心大家的学习进度，希望大家及时沟通并提问。
- Alice：继续学习MyBatis
- 小舒：学习springboot，完成todolist
- cherie：学习todolist代码


# Date: 30Apr2022

## 上周任务汇报

- @shaw（微信）
    - 小程序调研的文档不知道更新在哪里
    - 看了小程序mongo论坛功能，有回复。
    - 微信接口存储
    - 用户权限不明显，没有后台管理系统权限，操作数据库（简陋）
  
- @luying
没参加


- @锅巴：
   - 学习了微信小程序，并且自己尝试上线了微信小程序来了解这一流程，微信小程序需要开通开发者权限，需要提交身份证以及相关资格证，小程序有自己的云后台数据库管理
   - 调研了litemall商城，调研文档链接
   - 搭建了后端技术组的技术博客文档


- cherie
  - 学习了目前文档，熟悉当前的需求
  - 对于小程序进行了学习，还没有run



- @小舒：
- 有web管理系统、web学生系统、小程序端。小程序端实际上是实现了web端学生系统的功能。web管理系统主要功能是管理学生和发布试卷等任务，使用者应为老师。学生系统和小程序主要功能是学生练习及上交试卷，使用者为学生。我们的app设计时，发布待领养动物和领养动物的系统需要分开设计吗？（一点小疑问，因为我记得我们app定位是由NPO组织来发布待领养动物。

- @Alice
    - 商用版和非商用版区别，不太清楚springboot框架，准备跟课
    - 小程序
    - pm想要做的事情太多，网页ios andorid都想要，对我们太冗杂
    - spring cloud和spring boot的区别


## Discussion

- 数据库：确定为mysql，比较容易上手
- cherie完成一个简单的数据库可跑的本地数据库
- 还是先完成本地可跑的用户管理系统，可以先不管小程序，当做后端开发来做
- alice：大家还是应该把文档放到git上，在开会之前，google docs完成
- cherie:文档信息过于分散，git,slack微信,goole docs,zoom软件过多


## 下周任务
- alice:学习spring boot+小程序,数据库
- 锅巴：mybatis文档，如何部署项目写文档
- cherie: 项目跑成功,数据库的增删查改
- shawn,小舒：跑项目并且写to do list实现最基本功能
- 下周的会议记录由shaw开始。


# Date: 23Apr2022

## 上周任务汇报

- @shaw（微信）
  - 学习aws
  
- @luying
  - aws & AWS CICD 学习
  - 复习udemy springboot的课程

- @锅巴：
  - Springboot项目学习：[litemall](https://github.com/linlinjava/litemall)（springboot+vue+mysql)
  - 微信开发者工具stable， 微信需要
  - 专业技术文档

- @小舒：
  - 学习aws

- @Alice
  - 熟悉aws


## Discussion

- 第一阶段工作：
  - @锅巴：
    - 先搭框架实现单一功能
    - 最简单的竞品（？）：展示宠物照片和内容
  - @cherrie: 
    - 后端也需要visualisation
    - 先做一个MVP：minimum viable product 最小化可行产品
    - 用户发布照片
  - @Natalia: 4月24日上午的UIUX会议会讨论需求，一周后TPM静静会给完整需求

- 数据库：
  - @cherie: 
    - 数据有很多层，最初数据就是log
    - AI的model需要大量、清晰数据
    - 前期不可能考虑AI来搭建数据库，后期有需要可以进行数据导出、迁移、清洗


- 其他：

  - @cherie:
    - 如果在aws上进行操作，要小心超预算
    - 公众号文章推荐：[从0开始小程序](https://mp.weixin.qq.com/s/vfN0WvSYESSHEKYyRhiMRg)
  - @小舒：上周静静参加会议有讲是先网页开发，再小程序。具体如何？
    - @Natalia：4月24日上午的会议会reveal整个项目的规划

  - @Alice：微信作为工作工具不是非常上手，但slack国内需要VPN一直在线不方便，而github不适合实时交流
    - @cherie：可内部先尝试一下各种工具，但最好所有组统一使用相同的工具，方便信息集中共享


## 下周任务

- 先通过跑github上开源的用`springboot`框架的小程序项目来学习小程序，并分享相关信息在[GitHub](resources/survey_wechat_mini_programs.md)上。

- 根据4月24日上午的会议来确定后端组短期内的时间和要求

  


# [Date: 16Apr2022 wechat]
（会后讨论）

@狗跑：

1. 语言： java是国内目前后端主流语言，python不是，应该更加考虑学习成本和应用范围
2. 数据库：和AI共享数据库不现实。后端是online data，AI是offline data，后端业务字段不应该和AI特征值字段耦合。应该使用两个数据库。
3. 技术选型：后端开发应先当成没有AI组，功能需要逐步迭代；并且前期数据不够。
4. 可能需要一个技术上更有经验的人做决策。



@Alice：

经过内部讨论和探讨，决定将语言确定为java，框架spring boot。数据库会在下次和AI、PM的会议中确定。



# Date: 16Apr2022

## 上周任务汇报

- @Alice：上周工作比较忙，这周补上
- @luying(wechat)：fastapi对新手更友好

  - Automatic documentation 
  - No need of postman
  - Data validation
- @shaw: 没有倾向

  - django：功能完善，但响应速度相对慢
  - flask、fastapi：自己开发的部分比较多，但更小巧
- @小舒：

  - django主要支持的是关系型数据库，没有mongodb官方
  - flask数据库支持mongodb
- 锅巴：aws的使用iam身份认证，权限设置
  - 已设置1个30gb，另2个1024GB
  


## Discussion

- @natialia：前端会议做网页，tob toc网页版；第一版会是html
- 数据库的讨论
  - 用什么数据库？
    - 目前来看，我们依旧需要关系型数据库，一对多的关系。
    - @锅巴：不太确定产品给我们的需求，表结构设定。
  
  - 和AI组协作
    - @狗跑：AI 和后端独立，数据库独立。
    - @锅巴：需要考虑怎么调用AI的模型，无论是用tensorflow还是pytorch。
    - @Natalia： Ai组想达到的技术功能，通过照片/视频识别毛色，优化图片的质量，提高动物被领养的几率，推荐算法，领养人的黑名单
  
- CICD

  - @狗跑 的工作经验：
    - 代码部署：先去检测自动执行，代码打包到aws，代码手动上线到k8s
    - git actions 自带详细的文档 链接aws
    - 环境变量：所有可以默认的配置文件，放到github上
- @Natalia：会议记录轮流记录加在同一个文件；github上专门一个文件来装reference links
- @Natalia：四月底五月初，uxui会出结果

## 下周任务

- @Alice：github actions

- @锅巴：aws user；根据CICD文档来部署CICD文档；尝试部署django for testing；
  - 自己的用户组策略分配(权限json)，数据库的权限策略分配，将会和papa协商

- @小舒 & @shaw：学习aws，python后端开发的知识
- @狗跑：在仓库里用github actions打包上传
- jingjing：项目管理留档很重要；团队同频很重要；项目管理工具miro



# Date: 09Apr2022

## 上周任务汇报

- @锅巴：08Apr2022才得到aws的权限打开，接下来继续完善教程
- @Alice：git actions调研
  - Public repository 免费
  - 前端、后端、AI组都需要建立
  - 需要继续调研现有workflow来实现CICD
- @小舒：数据库
  - 上周确定nosql后，比较mongodb 和 dynamoDB

  - dynamoDB：API和mysql比较相似

  - 功能上都可以实现我们的功能，索引上稍微有不同

  - `mongodb`可以用多种`cloud`，但部署上在AWS上会更麻烦

  - 部署在云端都要收费，
    - `mongodb`: pay as you go

    - `dynamodb`: 一开始会更便宜
- @shaw: TPM 确定是先做小程序
  - 前端开发框架：
    - 主流框架：类vue和类react，都支持跨端
      类VUE: uni-app（支持跨端：支付宝/微信）
        使用 Vue.js 开发小程序、H5、App的统一前端框架。  
        *github：https://github.com/dcloudio/uni-app*
      类React: taro
        开源的一款使用 React.js 开发微信小程序的前端框架。
        *github地址：https://github.com/NervJS/taro*
      类Vue: WePY（最近官方更新：2020.6.4）
        *github：https://github.com/Tencent/wepy*
      类VUE: mpvue（已停止维护）
        美团团队开源的一款使用 Vue.js 开发微信小程序的前端框架。（已经停止维护）
        *github地址：https://github.com/Meituan-Dianping/mpvue*
    
  - 问题：小程序前端限制更多，后端没有限制，选择后端需要做哪些考量？



## Discussion

- @Natalia: 要与前端讨论是否一开始使用小程序，应该本周会得出结论
- 小程序前端限制更多，后端没有限制，选择后端需要做哪些考量？
  - 前后端分离的话，不需要考虑平台。
- framework: 既然确定用python，那需要在Django VS Flask选择

- @锅巴：aws 虚拟机 ubuntu: 18.04

## 下周任务

- Luying, Shaw, 小舒：
  - 调研flask和django，记录在hackmd上
  - 数据库调研@小舒：可以和AI组的Jeni姐妹配合调研，如果有必要的话可以协商@Natalia 约一个后端组与AI组的跨组会议
  - 调研flask和django，记录在hackmd
- 锅巴：继续aws部署学习
- Alice: 继续git actions 学习

# Date: 03Apr2022

## 上周任务汇报

- @Shaw

  - 查看了官方文档：完善基本信息进行审核

  - 提交源代码审核

  - 开发语言：前端： 微信自己开发 后端

  - 现有的程序，有很多已有的领养，用户不活跃

- @锅巴：安卓 iOS 官方文档

  - 安卓开发者文档：Kotlin 静态编程语言；运行下载andriod studio 编译， 语言更偏向于java

  - iOS：开发文档，swift；苹果应用商店注册备案

- @小舒：数据库

  - `mongodb`：开发更快，但开源，不限制平台

  - `dynamodb`：`aws`专有，需要继续研究

  - 微信小程序数据库：和`mongodb`差不多

- @Alice：定下aws+k8s的话，我们就需要使用aws的eks

## 本次会议讨论内容

#### 1.  数据库

   - @papa：当数据库确实时，需要和AI组商讨；但需要更倾向于平台不受限的db，just in case of migrating the cloud if necessary.

#### 2. CI/CD:

   - @Alice: 前端后端都需要。

#### 3. peer programming and code review

   - Peer programming: 可以，内部或者邀请senior
   - Code review: 大家都觉得需要
   - @锅巴：也需要代码规范的确定

#### 4. 语言: 

   - AI & 前端更希望python
   - 内部还需等全组人员到齐商讨

#### 5. 第一阶段任务

- @papa：第一个阶段主要是小程序（功能实现）+ webapp（仅显示项目信息）
- @papa：所以现在学习重心可以放在小程序及相关，aws和docker
- @papa：5月初PRD，数据库应该可以明确表头

## 下周任务

- Luying: (未定)
- Shaw：框架
- 小舒：数据库
- 锅巴：aws服务器部署学习，调查在国内部署是否存在问题
- Alice: CICD
