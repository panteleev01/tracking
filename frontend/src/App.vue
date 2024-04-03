<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import { useAuthStore } from '@/stores/auth'
import router from '@/router';

const store = useAuthStore();

function createExercise() {
  router.push('/add-exercise');
}

function createWorkout() {
  router.push('/add-workout');
}

function logout() {
  store.logout();
}

function register() {
  router.push('/registration');
}

function login() {
  router.push('/login');
}

</script>

<template>
  <div class="min-h-screen bg-gray-100">
    <header class="p-8">
      <nav class="flex flex-row list-none">
        🍃🥾
        <RouterLink to="/" class="ml-4">Dashboard</RouterLink>
        <RouterLink to="/exercises" class="ml-4">Exercises</RouterLink>
        <RouterLink to="/workouts" class="ml-4">Workouts</RouterLink>

        <div v-if="store.user" style="width: 100%; display: flex;" >
          <a href="#" class="ml-4 text-indigo-400" @click="logout">Logout</a>

          <button class="favorite styled button" style="margin-left: auto; height: 50px;"  type="button" @click="createExercise">Create exercise</button>
          <button class="favorite styled button" style="margin-left: 3%; margin-right: 5%; height: auto;" type="button" @click="createWorkout">Create workouts</button>
          <div id="box" style="text-align: center; padding-top: 10px; height: 50px; font-size: large;">
            {{ store.user }}
          </div>
        </div>
        <div v-else style="width: 500px;">
          <a href="#" class="ml-4 text-indigo-400" @click="register">Register</a>
          <a href="#" class="ml-4 text-indigo-400" @click="login">Login</a>
        </div>

        
      </nav>
    </header>
    <main class="p-8 w-1/2">
      <RouterView />
    </main>
  </div>
</template>


<style>
  .button {
    display: block; 
    margin-bottom: 10px; 
    padding: 10px;
    color: 2px solid black;
    border: 2px solid black;
    border-radius: 2%;
    width: 150px;
    height: 50px;
    text-align: center;
  }
</style>