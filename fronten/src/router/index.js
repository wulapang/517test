import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: () => import("@/components/loginn"),
    },
    {
      path: '/index',
      name: 'index',
      component: () => import("@/components/index"),
      children: [
        {
          path: 'table',
          name: 'HelloWorld',
          component: HelloWorld
        }
      ]
    }
  ]
})
