import Vue from 'vue'
import Router from 'vue-router'
import UserLogin from '../views/UserLogin'
import StoreLogin from '../views/StoreLogin'
import UserRegist from '../views/UserRegist'
import StoreRegist from '../views/StoreRegist'
import Storemanage from '../views/Storemanage'
import Homepage from '../views/Homepage'
import Userinfo from '../views/Userinfo'
import Market from '../views/Market'
import AdminPage from '../views/AdminPage'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/userlogin',
      name: 'userlogin',
      component: UserLogin
    },
    {
      path: '/storelogin',
      name: 'storelogin',
      component: StoreLogin
    },
    {
      path: '/userregist',
      name: 'userregist',
      component: UserRegist
    },
    {
      path: '/storeregist',
      name: 'storeregist',
      component: StoreRegist
    },
    {
      path: '/storemanage',
      name: 'storemanage',
      component: Storemanage
    },

    {
      path: '/homepage',
      name: 'homepage',
      component: Homepage,
      children:[
        {
          path:'/homepage/userinfo',
          name:'userinfo',
          component:Userinfo
        },
        {
          path:'/homepage/market',
          name:'market',
          component:Market
        },
        {
          path:'adminpage',
          name:'adminpage',
          component:AdminPage
        }
      ]
    }
  ]
})
