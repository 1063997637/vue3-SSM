import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import(/* webpackChunkName: "about" */ '../HomePage.vue'),
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    children: [
      // {
      //     path: "/dashboard",
      //     name: "dashboard",
      //     meta: {
      //         title: '系统首页'
      //     },
      //     component: () => import ( /* webpackChunkName: "dashboard" */ "../views/DashBoard.vue")
      // },
    ],
  },
  {
    path: '/test',
    name: 'test',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/TestVue.vue')
  },
  
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
