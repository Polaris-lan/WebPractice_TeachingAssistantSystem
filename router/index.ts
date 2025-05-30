import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: ()=>import('../views/Login.vue'),
    },
	{
	  path: '/home',
	  name: 'home',
	  component: ()=>import('../views/Home.vue'),
	  children:[
		  {
		    path: 'student',
		    name: 'student',
		    component: ()=>import('../views/Student.vue'),
		  },
		  {
		    path: '/a',
		    name: 'a',
		    component: ()=>import('../views/A.vue'),
		  },
		  {
		    path: '/b',
		    name: 'b',
		    component: ()=>import('../views/B.vue'),
		  },
		  {
		    path: 'dashboard',
		    name: 'Dashboard',
		    component: ()=>import('../views/Dashboard.vue'),
		  },
		  {
		    path: 'student-managemen',
		    name: 'StudentManagement',
		    component: ()=>import('../views/StudentManagement.vue'),
		  },
	  ]
	},
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

export default router
