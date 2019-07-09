<template>
  <div class="hello">
    <div class="layout">
           <Layout>
               <Sider ref="side1" class="mysider" :style="{height: '668px'}" hide-trigger collapsible
                width="220"
                :collapsed-width="64" v-model="isCollapsed">
                 <div class= "nav_wrap" :style="{width :getNavWrap}">
                   <ul class= "nav_ul" :style="{width :getNavUl}">
                     <el-menu default-active="1-4-1"
                     background-color="#515a6e"
                     text-color="#fff"
                     active-text-color="#ffd04b"
                     class="el-menu-vertical"
                     @open="handleOpen" @close="handleClose" :collapse="isCollapsed">
                       <el-submenu index="1" show-timeout=100 hide-timeout=100>
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

                   </ul>
                 </div>
               </Sider>
               <Layout>
                   <Header :style="{padding: 0}" class="layout-header-bar">
                       <Icon @click.native="collapsedSider" :class="rotateIcon" :style="{margin: '0 20px'}" type="md-menu" size="24"></Icon>
                   </Header>
                   <Content :style="{margin: '20px', background: '#fff', minHeight: '260px'}">
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
                isCollapsed: false
            }
        },
        computed: {
            getNavWrap () {
               return this.isCollapsed ? '64px' : '220px'
            },
            getNavUl () {
               return this.isCollapsed ? '89px' : '245px'
            }
        },
        methods: {
            collapsedSider () {
                this.$refs.side1.toggleCollapse();
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.layout{
       border: 1px solid #d7dde4;
       background: #f5f7f9;
       position: relative;
       border-radius: 4px;
       overflow: hidden;
   }
   .layout-header-bar{
       background: #fff;
       box-shadow: 0 1px 1px rgba(0,0,0,.1);
   }
   .el-menu-vertical{
     border: solid 0px ;
     width: auto;
     height: 100%;
   }
   .nav_wrap{
     height: 100%;
     overflow: hidden;
     padding: 0px;
     margin: 0px;
     border: 0px solid #ccc;
   }
   .nav_ul{
     height: 100%;
     padding: 0px ;
     margin: 0px 20px 0 0 ;
     overflow-y: scroll;
     overflow-x: hidden;
   }

   .nav_ul > *{
     border: 0px ;
   }


</style>
