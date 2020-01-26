import Vue from 'vue'
import Router from 'vue-router'
import Client from '../components/Client'
import Employee from '../components/Employee'
import Product from '../components/Product'
import Commande from '../components/Commande'
import Login from '../components/Login'
import Index from '../components/Index'

Vue.use(Router)

export const menuRouter = [
  {
    path: '/', redirect: '/login'
  },
  {
    path: '/login', component: Login
  },
  {
    path: '/index',
    component: Index,
    children: [
      {
        path: '/client/list', component: Client
      },
      {
        path: '/employee/list', component: Employee
      },
      {
        path: '/product/list', component: Product
      },
      {
        path: '/commande/list', component: Commande
      }
    ]
  }
]
export default new Router({
  routes: menuRouter
})
