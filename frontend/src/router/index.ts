import { createRouter, createWebHistory } from 'vue-router'
import DashboardView from '@/views/DashboardView.vue'
import LoginView from '@/views/LoginView.vue'
import AddExerciseView from '@/views/AddExerciseView.vue'
import AddWorkoutView from '@/views/AddWorkoutView.vue'
import { useAuthStore } from '@/stores/auth'
import ExercisesViewVue from '@/views/ExercisesView.vue'
import WorkoutsViewVue from '@/views/WorkoutsView.vue'
import RegistrationView from '@/views/RegistrationView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: DashboardView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView    
    },
    {
      path: '/add-exercise',
      name: 'add_exercise',
      component: AddExerciseView
    },
    {
      path: '/add-workout',
      name: 'add_workout',
      component: AddWorkoutView
    },
    {
      path: '/exercises',
      name: 'exercises',
      component: ExercisesViewVue
    },
    {
      path: '/workouts',
      name: 'workouts',
      component: WorkoutsViewVue
    },
    {
      path: '/registration',
      name: 'registration',
      component: RegistrationView
    }
  ]
});

router.beforeEach(async (to) => {
  console.log("here");
  // redirect to login page if not logged in and trying to access a restricted page
  const publicPages = ['/login', '/registration'];
  const authRequired = !publicPages.includes(to.path);
  const auth = useAuthStore();

  if (authRequired && !auth.user) {
      auth.returnUrl = to.fullPath;
      return '/login';
  }
});


export default router
