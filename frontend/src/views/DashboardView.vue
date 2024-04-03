<script lang="ts" setup>
import { useAuthStore } from '@/stores/auth';
import router from '@/router';

const store = useAuthStore();
const token = store.token;

function createExercise() {
  router.push('/add-exercise');
}

function createWorkout() {
  router.push('/add-workout');
}



</script>

<template>

  
  <h1 class="text-3xl mb-8">Dashboard</h1>

  <!-- <button class="favorite styled button" type="button" @click="createExercise">Create exercise</button>
  <button class="favorite styled button"  type="button" @click="createWorkout">Create workouts</button> -->

</template>

