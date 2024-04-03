<script lang="ts" setup>

import router from '@/router';
import { useAuthStore } from '@/stores/auth';
import { onMounted, ref } from 'vue';
import { computed } from 'vue';


const store = useAuthStore();
const token = store.token;

const data = ref<E[]>([]);

class E {
  id: number = 0;
  name: String = "";
  description: String = "";
}

onMounted(async () => {
  const response = await fetch('http://localhost:8080/list-all', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }).then(res => res.json())
  .then(arr => {
    data.value = arr as E[];
  }
  );
});

const selected = ref<number[]>([]);
const workoutName = ref<String>("");

async function save() {
    const name = workoutName.value;
    const ids = selected.value;
    const response = await fetch('http://localhost:8080/add-workout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({ name, ids })
    })
    router.back();
}


const isDisabled = computed(() => workoutName.value === '' || selected.value.length === 0);

</script>

<template>
    <div id="app">
      <div class="mb-4">
        <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Name</label>
        <input type="text" id="name" placeholder="Name" v-model="workoutName"
          class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
      </div>

      <h1 v-if="data.length > 0">
        <fieldset>
        <div v-for="(item, index) in data" :key="index">
          <div>
            <input type="checkbox" :value="item.id" v-model="selected">
            <label>{{ item.name }}</label><br>
          </div>
        </div>
      </fieldset>
      </h1>
      <h1 v-else>
        You don't have saved exercises.
      </h1>

      <span>Выбрано: {{selected}}</span>

      <div class="flex items"
         style="padding-top: 20px;">

        <button
          :disabled="isDisabled"
          class = "button rounded validated-button"
          :class="{ disabledInput: isDisabled }"
          style="width: 200px;"
          type="submit"
          @click="save">
          Save workout
        </button>

      </div>

    </div>
</template>

<style scoped>

@import '@/assets/validatedButton.css';

</style>
