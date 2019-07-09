<template>
  <div class="hello">

    <div class="layout">
        <Layout>
            <Sider ref="side1" class="menu-contain"
            :style="{position: 'fixed', height: '671px', left: 0, padding:0, margin:0}"
            hide-trigger collapsible width="220" :collapsed-width="60" v-model="isCollapsed">
              <!--<div v-if="isScrollTop && !isCollapsed" style="background-color: #2b85e4" > <img class="logo" src="../assets/logo1.png"/></div>
                <div :class="[!isScrollTop && isCollapsed ? 'top_zero' :'', rotateIcon,'top-icon']" @click="collapsedSider"
                :style="{width: getIconWidth,height: getIconHeight,padding: '10px 10px'}" >
                <Icon class="top-icon-icon" type="md-menu" size="24"></Icon>
              </div>
              <div style="color: #fff; margin: 20px 0;  font-size: 16px"> 版本 v1.2.1 </div>
              <el-radio-group v-model="isCollapsed" style="margin-bottom: 20px;">
                  <el-radio-button :label="false">展开</el-radio-button>
                  <el-radio-button :label="true">收起</el-radio-button>
              </el-radio-group>

              <div class="title">管理系统</div>

              <el-menu default-active="1-4-1"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
              class="el-menu-vertical"
              @open="handleOpen" @close="handleClose" :collapse="isCollapsed">
                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-location"></i>
                    <span slot="title">导航一</span>
                  </template>
                  <el-menu-item-group>
                    <span slot="title">分组一</span>
                    <el-menu-item index="1-1">选项1</el-menu-item>
                    <el-menu-item index="1-2">选项2</el-menu-item>
                    <el-menu-item index="1-3">选项2</el-menu-item>
                    <el-menu-item index="1-4">选项2</el-menu-item>
                    <el-menu-item index="1-5">选项2</el-menu-item>
                    <el-menu-item index="1-6">选项2</el-menu-item>
                    <el-menu-item index="1-7">选项2</el-menu-item>
                    <el-menu-item index="1-8">选项2</el-menu-item>
                    <el-menu-item index="1-9">选项2</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="分组2">
                    <el-menu-item index="1-3">选项3</el-menu-item>
                  </el-menu-item-group>
                  <el-submenu index="1-4">
                    <span slot="title">选项4</span>
                    <el-menu-item index="1-4-1">选项1</el-menu-item>
                  </el-submenu>
                </el-submenu>
                <el-menu-item index="2">
                  <i class="el-icon-menu"></i>
                  <span slot="title">导航二</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                  <i class="el-icon-document"></i>
                  <span slot="title">导航三</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <i class="el-icon-setting"></i>
                  <span slot="title">导航四</span>
                </el-menu-item>
              </el-menu>
-->

            <div class= "nav_wrap">
              <ul class= "nav_ul">
                <el-menu default-active="1-4-1"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b"
                class="el-menu-vertical"
                @open="handleOpen" @close="handleClose" :collapse="isCollapsed">
                  <el-submenu index="1">
                    <template slot="title">
                      <i class="el-icon-location" style="text-align: center"></i>
                      <span slot="title">导航一</span>
                    </template>
                    <el-menu-item-group>
                      <span slot="title">分组一</span>
                      <el-menu-item index="1-1">选项1</el-menu-item>
                      <el-menu-item index="1-2">选项2</el-menu-item>
                      <el-menu-item index="1-3">选项2</el-menu-item>
                      <el-menu-item index="1-4">选项2</el-menu-item>
                      <el-menu-item index="1-5">选项2</el-menu-item>
                      <el-menu-item index="1-6">选项2</el-menu-item>
                      <el-menu-item index="1-7">选项2</el-menu-item>
                      <el-menu-item index="1-8">选项2</el-menu-item>
                      <el-menu-item index="1-9">选项2</el-menu-item>
                    </el-menu-item-group>
                    <el-menu-item-group title="分组2">
                      <el-menu-item index="1-3">选项3</el-menu-item>
                    </el-menu-item-group>
                    <el-submenu index="1-4">
                      <span slot="title">选项4</span>
                      <el-menu-item index="1-4-1">选项1</el-menu-item>
                    </el-submenu>
                  </el-submenu>
                  <el-menu-item index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title">导航二</span>
                  </el-menu-item>
                  <el-menu-item index="3" disabled>
                    <i class="el-icon-document"></i>
                    <span slot="title">导航三</span>
                  </el-menu-item>
                  <el-menu-item index="4">
                    <i class="el-icon-setting"></i>
                    <span slot="title">导航四</span>
                  </el-menu-item>
                </el-menu>

              </ul>
            </div>

            </Sider>
            <Layout>
                <Header :style="{padding: 0}" class="layout-header-bar">

                </Header>
                <Content :style="{margin: '80px 20px 20px 20px', background: '#fff', minHeight: '1200px'}">
                    Content
                </Content>
            </Layout>
        </Layout>


    </div>
  </div>
</template>

<script>
  export default {
        data () {
            return {
                isCollapsed: false,
                affix : true,
                isScrollTop: true

            }
        },
        computed: {
            rotateIcon () {
                return [
                    'menu-icon',
                    this.isCollapsed ? 'rotate-icon' : ''
                ];
            },
            menuitemClasses () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            },
            getIconWidth(){
               return  this.isCollapsed ? '60px' : '220px'
            },
            getIconHeight(){
               return  this.isCollapsed ? '60px' : '60px'
            }
        },
        methods: {
            collapsedSider () {
                this.$refs.side1.toggleCollapse();
            }

        },
        mounted(){

        },
        created(){
            console.log(document.documentElement.clientHeight || document.body.clientHeight);
          var that = this;
          window.onscroll = function(){
            //变量scrollTop是滚动条滚动时，距离顶部的距离
            var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
            //变量windowHeight是可视区的高度
            var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
            //变量scrollHeight是滚动条的总高度
            var scrollHeight = document.documentElement.scrollHeight||document.body.scrollHeight;

               //滚动条到底部的条件
               if(scrollTop+windowHeight==scrollHeight){
                //写后台加载数据的函数
                console.log("距顶部"+scrollTop+"可视区高度"+windowHeight+"滚动条总高度"+scrollHeight);
              }

              if(scrollTop< 10){
                 if(!that.isScrollTop){
                   that.isScrollTop= true
                 }
              }else{
                if(that.isScrollTop){
                  that.isScrollTop= false
                }
              }

              //滚动条到底部的条件
              if(scrollTop+windowHeight>scrollHeight- 40){
                 //写后台加载数据的函数
                 that.affix = false
               }else{
                 if(!that.affix){
                    that.affix = true
                 }
               }

            }
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.title{
  text-align: center;
  color: #fff;
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 20px;
}
.layout{
       border: 1px solid #d7dde4;
       background: #f5f7f9;
       position: relative;
       border-radius: 4px;
       overflow: hidden;

   }
   .layout-header-bar{
      position: fixed;
      z-index: 1000;
      height: 60px;

       background: #5cadff;
       box-shadow: 0 1px 1px rgba(0,0,0,.1);
   }
   .layout-logo-left{
       width: 90%;
       height: 30px;
       background: #5b6270;
       border-radius: 3px;
       margin: 15px auto;
   }
   .top_zero{
     top : 0;
   }
   .logo{
      transition: all .3s;
     margin-top: 20px;
     width: 100px;
   }
   .menu-icon{
       transition: all .3s;
   }
   .menu-contain{
     display: block;

   }
   .top-icon{
      align-content: center;
      transition: all 0.3s;
      position: fixed;
      display: block;
      background-color: #2b85e4;
      z-index: 1000;
      overflow:hidden;
   }
   .top-icon-icon{
     margin-top: 10px;
   }
   .el-menu-vertical{
     transition: all 0s;
     border-right: solid 0px ;
     width: auto;
   }
   .rotate-icon{
       transform: rotate(-90deg);
   }
   .menu-item {
     padding-top: 78px;
   }
   .menu-item span{
       display: inline-block;
       overflow: hidden;
       width: 70px;
       text-overflow: ellipsis;
       white-space: nowrap;
       vertical-align: bottom;
       transition: width .2s ease .2s;
   }
   .menu-item i{
       transform: translateX(0px);
       transition: font-size .2s ease, transform .2s ease;
       vertical-align: middle;
       font-size: 16px;
   }
   .collapsed-menu span{
       width: 0px;
       transition: width .2s ease;
   }
   .collapsed-menu i{

       transition: font-size .2s ease .2s, transform .2s ease .2s;
       vertical-align: middle;
       font-size: 20px;
   }


   .nav_wrap{
     height: 100%;
     width: auto;
     overflow: hidden;
     padding: 0px;
     margin: 0px;
     border: 1px solid #ccc;

   }
   .nav_ul{
     height: 100%;
     width: auto;
     padding-right: 10px;
     margin: 0px;
     overflow-y: scroll;
     overflow-x: hidden;
   }

</style>
