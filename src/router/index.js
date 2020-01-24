import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
import AddClient from '../components/Client/AddClient'
import AddEmployee from '../components/Employee/AddEmployee'
import AddProduct from '../components/Product/AddProduct'
import Client from '../components/Client/Client'
import Employee from '../components/Employee/Employee'
import Product from '../components/Product/Product'
import Commande from '../components/Commande/Commande'
import AddCommande from '../components/Commande/AddCommande'
import UpdateClient from '../components/Client/UpdateClient'
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
        path: '/home', component: Home
      },
      {
        path: '/client/list', component: Client
      },
      {
        path: '/client/add', component: AddClient
      },
      {
        path: '/client/update', component: UpdateClient
      },
      {
        path: '/employee/list', component: Employee
      },
      {
        path: '/employee/add', component: AddEmployee
      },
      {
        path: '/product/list', component: Product
      },
      {
        path: '/product/add', component: AddProduct
      },
      {
        path: '/commande/list', component: Commande
      },
      {
        path: '/commande/add', component: AddCommande
      }
    ]
  }
]
export default new Router({
  routes: menuRouter
})
