<template>
  <div id="app">
    <div class="layout">
           <Layout>
               <Sider ref="side1" class="mysider" :style="{height: WHeight}" hide-trigger collapsible
                width="220"
                :collapsed-width="64" v-model="isCollapsed">
                 <div class= "nav_wrap" :style="{width :getNavWrap}">
                   <ul class= "nav_ul" :style="{width :getNavUl}">
                     <el-menu :default-active="selectHeadTag"
                     background-color="#515a6e"
                     text-color="#fff"
                     active-text-color="#ffd04b"
                     :default-openeds="openMenu"
                     class="el-menu-vertical"
                     @select="menuSelect"
                     @open = "menuOpen"
                     :collapse="isCollapsed">
                       <el-menu-item index="0">
                         <Icon type="ios-analytics" size="19" style="margin: 0 7.5px 0 2px" />
                         <span slot="title">总预览</span>
                       </el-menu-item>
                      <!-- <el-submenu index="1" >
                         <template slot="title">
                           <i class="el-icon-location"></i>
                           <span slot="title">操作</span>
                         </template>
                         <el-menu-item-group>
                           <span slot="title">分组一</span>
                           <el-menu-item index="1-1" >表格</el-menu-item>
                          <el-menu-item index="1-2">添加</el-menu-item>
                         </el-menu-item-group>
                       </el-submenu> -->
                       <el-submenu index="2" >
                         <template slot="title" >
                           <i class="el-icon-location"></i>
                           <span slot="title" >主页</span>
                         </template>
                      <!--   <el-menu-item-group title="总览">
                          <el-menu-item index="2-1" disabled>表格</el-menu-item>
                          <el-menu-item index="2-2" disabled>添加</el-menu-item>
                         </el-menu-item-group>-->
                         <el-menu-item-group title="修改主页" >
                           <el-menu-item index="2-3" >大图部分</el-menu-item>
                           <el-menu-item index="2-4">介绍部分</el-menu-item>
                           <el-menu-item index="2-5">优势和案例</el-menu-item>
                         </el-menu-item-group>
                       </el-submenu>

                       <el-submenu index="3" >
                         <template slot="title">
                           <i class="el-icon-location"></i>
                           <span slot="title">业务</span>
                         </template>
                        <!-- <el-menu-item-group title="总览">
                          <el-menu-item index="3-1" disabled>表格</el-menu-item>
                          <el-menu-item index="3-2" disabled>添加</el-menu-item>
                         </el-menu-item-group>-->
                         <el-menu-item-group title="修改业务">
                           <el-menu-item index="3-3">大图部分</el-menu-item>
                           <el-menu-item index="3-4">服务和案例</el-menu-item>
                         </el-menu-item-group>
                         <el-menu-item-group title="服务">
                           <el-menu-item index="3-5">服务表</el-menu-item>
                           <el-menu-item index="3-6">添加服务</el-menu-item>
                         </el-menu-item-group>
                       </el-submenu>

                       <el-submenu index="4" >
                         <template slot="title">
                           <i class="el-icon-location"></i>
                           <span slot="title">案例</span>
                         </template>
                      <!--   <el-menu-item-group title="总览">
                          <el-menu-item index="4-1" disabled>表格</el-menu-item>
                          <el-menu-item index="4-2" disabled>添加</el-menu-item>
                         </el-menu-item-group>-->
                         <el-menu-item-group title="修改案例">
                           <el-menu-item index="4-3">大图部分</el-menu-item>
                         </el-menu-item-group>
                         <el-menu-item-group title="案例">
                           <el-menu-item index="4-4">案例表</el-menu-item>
                           <el-menu-item index="4-5">添加案例</el-menu-item>
                         </el-menu-item-group>
                       </el-submenu>


                       <el-submenu index="5" >
                         <template slot="title">
                           <i class="el-icon-location"></i>
                           <span slot="title">教程文档</span>
                         </template>
                          <!-- <el-menu-item-group title="总览">
                          <el-menu-item index="5-1" disabled>表格</el-menu-item>
                          <el-menu-item index="5-2" disabled>添加</el-menu-item>
                         </el-menu-item-group>-->
                         <el-menu-item-group title="修改教程">
                           <el-menu-item index="5-3">大图部分</el-menu-item>
                         </el-menu-item-group>
                         <el-menu-item-group title="教程">
                           <el-menu-item index="5-4">教程表</el-menu-item>
                           <el-menu-item index="5-5">添加教程</el-menu-item>
                         </el-menu-item-group>
                         <el-menu-item-group title="文档">
                           <el-menu-item index="5-6">文档表</el-menu-item>
                           <el-menu-item index="5-7">添加文档</el-menu-item>
                         </el-menu-item-group>
                       </el-submenu>

                       <el-submenu index="6" >
                         <template slot="title">
                           <i class="el-icon-location"></i>
                           <span slot="title">关于我们</span>
                         </template>
                        <!--    <el-menu-item-group title="总览">
                          <el-menu-item index="6-1" disabled>表格</el-menu-item>
                          <el-menu-item index="6-2" disabled>添加</el-menu-item>
                         </el-menu-item-group>-->
                         <el-menu-item-group title="修改关于">
                           <el-menu-item index="6-3">大图部分</el-menu-item>
                           <el-menu-item index="6-4">经验和团队</el-menu-item>
                         </el-menu-item-group>
                         <el-menu-item-group title="经验">
                           <el-menu-item index="6-5">经验表</el-menu-item>
                           <el-menu-item index="6-6">添加经验</el-menu-item>
                         </el-menu-item-group>
                         <el-menu-item-group title="团队">
                           <el-menu-item index="6-7">团队表</el-menu-item>
                           <el-menu-item index="6-8">添加团队</el-menu-item>
                         </el-menu-item-group>
                       </el-submenu>
                       <el-menu-item index="7">
                         <Icon type="ios-analytics" size="19" style="margin: 0 7.5px 0 2px" />
                         <span slot="title">测试页</span>
                       </el-menu-item>

                     </el-menu>

                   </ul>
                 </div>
               </Sider>
               <Layout>
                   <Header :style="{padding: 0}" class="layout-header-bar">

                      <div class="head-top">
                          <div class="head-top-left">
                            <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{cursor: 'pointer', padding: '10px 10px'}" type="md-menu" size="24"></Icon>
                            <Icon :style="{padding: '10px 10px',cursor: 'pointer'}" type="ios-globe-outline" size="24"></Icon>
                            <Icon :class="rotateIcon" :style="{padding: '10px 10px',cursor: 'pointer'}" type="md-refresh" size="24"></Icon>
                            <Input style="display: inline-block; width: 30%; margin-left: 20px" search placeholder="Enter something..." />
                          </div>
                          <div class="head-top-right">
                            <Badge dot>
                                   <Icon style="cursor: pointer;" type="ios-notifications-outline" size="24"></Icon>
                            </Badge>
                            <span style="display: inline-block; width: 5%;"/>
                            <Poptip title="Title" content="content" placement="bottom">

                            </Poptip>


                            <Poptip  placement="bottom" width="400" >
                                <Badge dot>
                                    <Icon style="cursor: pointer;" type="ios-create-outline" size="24"></Icon>
                                </Badge>
                                <div class="api" slot="content">
                                    <Input  type="textarea" :rows="10" placeholder="Enter something..." />
                                </div>
                            </Poptip>

                              <span style="display: inline-block; width: 8%;"/>


                              <Poptip trigger="hover" width="100">
                                <span style="font-size: 16px;">
                                  管理员
                                  <Icon type="ios-arrow-down" />
                                </span>
                                <div class="api" slot="content" style="text-align: center; font-size: 16px;">
                                    <div class="per">个人资料</div>
                                    <div class="per">修改密码</div>
                                    <span class="line" style="margin: 0px 2px" />
                                    <div class="per">退出</div>
                                </div>
                              </Poptip>

                                <span style="display: inline-block; width: 5%;"/>

                                <Icon style="cursor: pointer;" size="24" @click.native="isDrawer = true" type="md-more" />

                                <Drawer title="Basic Drawer" :closable="false" v-model="isDrawer">
                                    <p>Some contents...</p>
                                    <p>Some contents...</p>
                                    <p>Some contents...</p>
                                </Drawer>
                          </div>
                      </div>
                      <span class="line" />
                      <div class="head-bottom">
                        <div class="head-bottom-btn" @mouseenter="srollyOverBtn(0)" @mouseleave="srollyleaveBtn()"   style="left: 0 ; top: 0" >
                          <i class="el-icon-d-arrow-left head-botton-icon"></i>
                        </div>
                          <div class="v_line" style="left: 35px"></div>
                        <div class="head-bottom-btn" style="left: 36px ; top: 0" >

                          <Icon type="ios-analytics" @click="selectIndex" :class="[{ 'head-bottom-btn-active': selectHeadTag=='0' }, 'head-botton-icon']"/>
                        </div>
                          <div class="v_line" style="left: 71px"></div>

                        <div class="head-tag" ref="headsrolly">
                            <!-- 中间 标签页 -->
                            <ul  class="srolly" >
                                <v-head-tag v-for="(tag, index) in headTag" :key="index"
                                :selectHeadTag = "selectHeadTag"
                                :tag_text="tag.value" :tag_index="tag.index"
                                :index="index" @removeMenu="removeMenu"
                                @selectMenu="selectMenu"></v-head-tag>
                            </ul>


                        </div>

                        <div class="v_line" style="right: 71px"></div>
                        <div  class="head-bottom-btn"  @mouseenter="srollyOverBtn(1)" @mouseleave="srollyleaveBtn()" style=" right: 36px ; top: 0" >
                          <i class="el-icon-d-arrow-right  head-botton-icon"></i>
                        </div>
                        <div class="v_line" style="right: 35px"></div>

                        <div  class="head-bottom-btn" @click="deleteAll" style="right: 0 ; top: 0" >
                          <i class="el-icon-delete head-botton-icon"></i>
                        </div>


                      </div>

                   </Header>
                   <Content :style="{margin: '15px', background: '#fff', minHeight: '260px'}">

                      <router-view :mHeight= 'mHeight'></router-view>


                   </Content>
               </Layout>
           </Layout>
       </div>
  </div>
</template>

<script>
// 导入组件
import head_tag from './head_tag'
import https from '../https.js'
export default {
  props: {
    mHeight: {
      type: String,
      default: "1000px"
    },
    WHeight: {
      type: String,
      default: "1000px"
    }
  },
  name: 'App',
  data () {
      return {
          isCollapsed: false,
          isDrawer: false,
          headTag:[],
          openMenu:[],
          selectHeadTag: "0"

      }
  },
  components:{
       "v-head-tag":head_tag
  },
  computed: {
      getNavWrap () {
         return this.isCollapsed ? '64px' : '220px'
      },
      getNavUl () {
         return this.isCollapsed ? '89px' : '245px'
      },
      rotateIcon () {
          return [
              'menu-icon',
              this.isCollapsed ? 'rotate-icon' : ''
          ];
      }

  },
  methods: {
    menuOpen(index,indexPath){
      this.openMenu = [index]
    },
    deleteAll(){
      this.headTag = []
      this.selectHeadTag ="0"
      this.$router.push({path: "/"})
    },
    pushHeadTag(index_value,tag_value, path){
      var that = this
      this.selectHeadTag = index_value
      if(that.headTag.length==0){
        that.headTag.push({index: index_value, value: tag_value,path: path})
      }else{
        var isExist = false
        that.headTag.find(function(tag) {
          if( tag.value == tag_value){
            isExist = true
          }
         })
         if(!isExist){
            that.headTag.push({index: index_value, value: tag_value,path: path})
         }
      }
    },
    menuSelect(index){

      switch (index) {
        case "0":
            this.selectHeadTag = "0"
            this.$router.push({path: "/"})
          break
        case "1-1":
            this.pushHeadTag("1-1","表单","/table")
            this.$router.push({path: "/table"})
          break;
        case "1-2":
            this.pushHeadTag("1-2","添加","/add")
            this.$router.push({path: "/add"})
          break;
        case "2-3":
            this.pushHeadTag("2-3","主页大图","/indexBigImg")
            this.$router.push({path: "/indexBigImg"})
          break;
        case "2-4":
            this.pushHeadTag("2-4","介绍部分","/indexIntroduce")
            this.$router.push({path: "/indexIntroduce"})
          break;
        case "2-5":
            this.pushHeadTag("2-5","优势案例","/indexAC")
            this.$router.push({path: "/indexAC"})
          break;
        case "3-3":
            this.pushHeadTag("3-3","业务大图","/businessBigImg")
            this.$router.push({path: "/businessBigImg"})
          break;
        case "3-4":
            this.pushHeadTag("3-4","服务案例","/businessSC")
            this.$router.push({path: "/businessSC"})
          break;
        case "3-5":
            this.pushHeadTag("3-5","服务表","/busSTable")
            this.$router.push({path: "/busSTable"})
          break;
        case "3-6":
            this.pushHeadTag("3-6","添加服务","/addService")
            this.$router.push({path: "/addService"})
          break;
        case "4-3":
            this.pushHeadTag("4-3","案例大图","/caseBigImg")
            this.$router.push({path: "/caseBigImg"})
          break;
        case "4-4":
            this.pushHeadTag("4-4","案例表","/caseTable")
            this.$router.push({path: "/caseTable"})
          break;
        case "4-5":
            this.pushHeadTag("4-5","添加案例","/addCase")
            this.$router.push({path: "/addCase"})
          break;
        case "5-3":
            this.pushHeadTag("5-3","教程大图","/courseBigImg")
            this.$router.push({path: "/courseBigImg"})
          break;
        case "5-4":
            this.pushHeadTag("5-4","教程表","/videoTable")
            this.$router.push({path: "/videoTable"})
          break;
        case "5-5":
            this.pushHeadTag("5-5","添加教程","/addVideo")
            this.$router.push({path: "/addVideo"})
          break;
        case "5-6":
            this.pushHeadTag("5-6","文档表","/fileTable")
            this.$router.push({path: "/fileTable"})
          break;
        case "5-7":
            this.pushHeadTag("5-7","添加文档","/addFile")
            this.$router.push({path: "/addFile"})
          break;
        case "6-3":
            this.pushHeadTag("6-3","关于大图","/aboutBigImg")
            this.$router.push({path: "/aboutBigImg"})
          break;
        case "6-4":
            this.pushHeadTag("6-4","经验团队","/aboutET")
            this.$router.push({path: "/aboutET"})
          break;
        case "6-5":
            this.pushHeadTag("6-5","经验表","/expertTable")
            this.$router.push({path: "/expertTable"})
          break;
        case "6-6":
            this.pushHeadTag("6-6","添加经验","/addExpert")
            this.$router.push({path: "/addExpert"})
          break;
        case "6-7":
            this.pushHeadTag("6-7","团队表","/teamTable")
            this.$router.push({path: "/teamTable"})
          break;
        case "6-8":
            this.pushHeadTag("6-8","添加团队","/addTeam")
            this.$router.push({path: "/addTeam"})
          break;
        case "7":
            this.pushHeadTag("7","测试页","/test")
            this.$router.push({path: "/test"})
          break;
        default:
      }
    },
    removeMenu(tag_index,index){
      this.headTag.splice(index,1);
      if(this.selectHeadTag != "0"){
          if(this.headTag.length>0){
            if(index>0){
                this.openMenu = [ this.headTag[index-1].index.substring(0,1)]
              this.selectHeadTag = this.headTag[index-1].index
              this.$router.push({path: this.headTag[index-1].path})
            }else{
              this.openMenu = [ this.headTag[0].index.substring(0,1)]
              this.selectHeadTag = this.headTag[0].index
              this.$router.push({path: this.headTag[0].path})
            }

          }else{
            this.selectHeadTag ="0"
            this.$router.push({path: "/"})
          }
      }
    },
    selectMenu(tag_index,index){
        this.openMenu = [tag_index.substring(0,1)]
      this.selectHeadTag = tag_index
      this.$router.push({path: this.headTag[index].path})
    },
    selectIndex(){
      this.selectHeadTag ="0"
      this.$router.push({path: "/"})
    },
    getleft(index){
      return 72+80*index+"px"
    },
    collapsedSider () {
        this.$refs.side1.toggleCollapse();
    },
    srollyOverBtn(right) {
      var that = this
      	clearTimeout(this.Loop); //再次清空定时器，防止重复注册定时器
        if(right == 1 ){
          this.Loop = setInterval(function(){
            that.$refs.headsrolly.scrollLeft= that.$refs.headsrolly.scrollLeft + 10
          }, 50);
        }else{
          this.Loop = setInterval(function(){
            that.$refs.headsrolly.scrollLeft= that.$refs.headsrolly.scrollLeft - 10
          }, 50);
        }

      },
    srollyleaveBtn() {
      console.log("清除");
    	clearTimeout(this.Loop);
    },
  },
  created(){

  },
  mounted(){
    // 判断 token 是否有效等，如果无效重新登录
    /*var store = require('store')
    if(typeof(store.get('token')) == 'undefined'){
        this.$router.push({path: '/login'})
    }else{
      https.fetchGet("/api/oauth/check_token",{'token': store.get('token')},{'Authorization':'Basic YWRtaW46e25vb3B9YWRtaW4='}).then((data) => {

                  })
                  .catch((err)=>{
                      this.$router.push({path: '/login'})

                  })
    }
    */
  }

}
</script>

<style scoped>

.per{
  margin: 3px 0;
  padding: 3px 0;
  cursor: pointer;
}
.per:hover{
  background-color: #DEDEDE;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
.layout{
   border: 1px solid #d7dde4;
   background: #f5f7f9;
   position: relative;
   border-radius: 4px;
   overflow: hidden;
}
.layout-header-bar{
  line-height: 0;
  margin: 0;
  width: auto;
  height: 85px;
   background: #fff;
   box-shadow: 0 1px 1px rgba(0,0,0,.1);
   overflow: hidden;
}
.line{
  display: block;
  height: 1px;
  width: auto;
  background-color: #DEDEDE;
}
.v_line{
  z-index: 1000;
  background-color: #DEDEDE;
  width: 1px;
  height: 100%;
  position: absolute;
  top: 0
}
.head-top{
  height: 49px;
  width: auto;
  line-height: 47px;
}
.head-top-left{
  float: left;
  height: 49px;
  width: 50%;
}
.head-top-right{
  float: right;
  line-height: 0;
  padding: 14px;
  text-align: right;
  height: 49px;
  width: 50%;
}
.head-bottom{
  height: 35px;
  width: auto;
  position: relative;
}
.head-bottom-btn{
  z-index: 1000;
  background-color: #fff;
   width: 35px;
   height: 100%;
   position: absolute;
  cursor: pointer;
}
.head-tag{
  background-color: #F8F8F8;
  overflow-y: hidden;
  overflow-x: scroll;
  width: auto;
  height: 100px;
  margin-left: 72px;
  margin-right: 72px;
  padding: 0;
  overflow-x:visible;
}
.srolly{
  display: flex;
}
.head-botton-icon{
  font-size: 20px;
  text-align: center;
  line-height: 36px;
  display: block;
}
.head-bottom-btn:hover{
  background-color: #DEDEDE;
}
.head-bottom-btn-active{
  background-color: #DEDEDE;
}
.el-menu-vertical{
 border: solid 0px ;
 width: auto;
 height: 100%;
}
.rotate-icon{
    transform: rotate(-90deg);
}
.menu-item {
  padding-top: 78px;
}
.nav_wrap{
 transition: all .2s;
 height: 100%;
 overflow: hidden;
 padding: 0px;
 margin: 0px;
 border: 0px solid #ccc;
}
.nav_ul{
 transition: all .3s;
 height: 100%;
 padding: 0px ;
 margin: 0px 20px 0 0 ;
 overflow-y: scroll;
 overflow-x: hidden;
}
.nav_ul > *{
 border: 0px ;
}
.contain_all{
  width: auto;
  transition: all 0s;
  height: auto;

}



</style>
