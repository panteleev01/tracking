<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { computed} from 'vue';
import { useAuthStore } from '@/stores/auth';
import router from '@/router';

const user = reactive({
  username: '',
  password: '',
});

async function onSubmit() {
    console.log(user);
    console.log("registering in processing");
    const username = user.username;
    const password = user.password;
    const response = await fetch('http://localhost:8080/registration', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
      });
    if (response.status == 200) {
        router.push('/login');
    }
}

const isDisabled = computed(() => user.username === '' || user.password === '');

</script>

<template>
  <h1 class="text-3xl mb-8">Registration</h1>
  <form class="bg-white p-8 rounded-md shadow-md" @submit.prevent="onSubmit">
    <div class="mb-4">
      <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Username</label>
      <input type="text" id="username" placeholder="Username" v-model="user.username"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
    </div>
    <div class="mb-6">
      <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password</label>
      <input type="password" id="password" placeholder="Password" v-model="user.password"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline">
    </div>
    <div class="flex items">
      <button
        :disabled="isDisabled"
        class = "button rounded validated-button"
        :class="{ disabledInput: isDisabled }"
        type="submit">
        Register
      </button>

    </div>
  </form>
</template>

<style scoped>

@import '@/assets/validatedButton.css';

</style>
