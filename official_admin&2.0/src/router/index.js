import Vue from 'vue'
import Router from 'vue-router'
import table from '@/components/table'
import chat from '@/components/chat'
import index from '@/components/index'
import add from '@/components/add'
import indexBigImg from '@/components/indexBigImg'
import indexIntroduce from '@/components/indexIntroduce'
import indexAC from '@/components/indexAC'
import businessBigImg from '@/components/businessBigImg'
import businessSC from '@/components/businessSC'
import busSTable from '@/components/busSTable'
import addService from '@/components/addService'
import caseBigImg from '@/components/caseBigImg'
import caseTable from '@/components/caseTable'
import addCase from '@/components/addCase'
import courseBigImg from '@/components/courseBigImg'
import videoTable from '@/components/videoTable'
import addVideo from '@/components/addVideo'
import fileTable from '@/components/fileTable'
import addFile from '@/components/addFile'
import aboutBigImg from '@/components/aboutBigImg'
import aboutET from '@/components/aboutET'
import addExpert from '@/components/addExpert'
import addTeam from '@/components/addTeam'
import expertTable from '@/components/expertTable'
import teamTable from '@/components/teamTable'
import appAll from '@/components/appAll'
import login from '@/components/login'
import test from '@/components/test'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path:'/',
      name: 'app',
      component: appAll,
      children: [
        {
          path: '/',
          name: 'index',
          component: index
        },
        {
          path: '/table',
          name: 'table',
          component: table
        },
        {
          path: '/add',
          name: 'add',
          component: add
        },
        {
          path: '/indexBigImg',
          name: 'indexBigImg',
          component: indexBigImg
        },
        {
          path: '/indexIntroduce',
          name: 'indexIntroduce',
          component: indexIntroduce
        },
        {
          path: '/indexAC',
          name: 'indexAC',
          component: indexAC
        },
        {
          path: '/businessBigImg',
          name: 'businessBigImg',
          component: businessBigImg
        },
        {
          path: '/businessSC',
          name: 'businessSC',
          component: businessSC
        },
        {
          path: '/busSTable',
          name: 'busSTable',
          component: busSTable
        },
        {
          path: '/addService',
          name: 'addService',
          component: addService
        },
        {
          path: '/caseBigImg',
          name: 'caseBigImg',
          component: caseBigImg
        },
        {
          path: '/caseTable',
          name: 'caseTable',
          component: caseTable
        },
        {
          path: '/addCase',
          name: 'addCase',
          component: addCase
        },
        {
          path: '/courseBigImg',
          name: 'courseBigImg',
          component: courseBigImg
        },
        {
          path: '/videoTable',
          name: 'videoTable',
          component: videoTable
        },
        {
          path: '/addVideo',
          name: 'addVideo',
          component: addVideo
        },
        {
          path: '/fileTable',
          name: 'fileTable',
          component: fileTable
        },
        {
          path: '/addFile',
          name: 'addFile',
          component: addFile
        },
        {
          path: '/aboutET',
          name: 'aboutET',
          component: aboutET
        },
        {
          path: '/aboutBigImg',
          name: 'aboutBigImg',
          component: aboutBigImg
        },
        {
          path: '/teamTable',
          name: 'teamTable',
          component: teamTable
        },
        {
          path: '/expertTable',
          name: 'expertTable',
          component: expertTable
        },
        {
          path: '/addTeam',
          name: 'addTeam',
          component: addTeam
        },
        {
          path: '/addExpert',
          name: 'addExpert',
          component: addExpert
        },
        {
          path: '/test',
          name: 'test',
          component: test
        }
      ]
    }
  ]
})
