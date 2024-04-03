<script lang="ts" setup>
import { computed, reactive} from 'vue';
import { useAuthStore } from '@/stores/auth';
import router from '@/router';
import { onMounted, ref } from 'vue';
// import Dropdown from 'primevue/dropdown';

const store = useAuthStore();
const token = store.token;

const exer = reactive({
  name: '',
  description: '',
  type: '',
});

async function handler() {
    const name = exer.name;
    const description = exer.description;
    const type = exer.type;
    const response = await fetch('http://localhost:8080/add-exercise', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({ name, description, type })
    })
    router.push('/');
}

const types = ref<String[]>([]);

onMounted(async () => {
  const response = await fetch('http://localhost:8080/list-types', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }).then(res => res.json())
  .then(arr => {
    types.value = arr as String[];
  }
  );

});

const isDisabled = computed(() => exer.name === '' || exer.description === '' || exer.type === '');

</script>

<template>
    <form class="bg-white p-8 rounded-md shadow-md" @submit.prevent="handler">

    <div class="mb-4">
      <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Name</label>
      <input type="text" id="name" placeholder="Name" v-model="exer.name"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
    </div>

    <div class="mb-6">
      <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Description</label>
      <input type="text" id="description" placeholder="Description" v-model="exer.description"
        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
    </div>

    
    <select v-model="exer.type" style="height: 50px; width: 200px;" 
      class="block text-gray-700 text-sm font-bold mb-2 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
      <option value="">Select an type</option>
      <option v-for="(item, index) in types" :key="index" :value="item">{{ item }}</option>
    </select>

    <div class="flex items"
         style="padding-top: 20px;">
      <button
        :disabled="isDisabled"
        class = "button rounded validated-button"
        :class="{ disabledInput: isDisabled }"
        type="submit">
        Add
      </button>
    </div>

  </form>
</template>


<style scoped>

@import '@/assets/validatedButton.css';

</style>
