<script lang="ts" setup>

import router from '@/router';
import { useAuthStore } from '@/stores/auth';
import { onMounted, ref } from 'vue';

const store = useAuthStore();
const token = store.token;

const data = ref<E[]>([]);

class E {
  id: number = 0;
  name: String = "";
  description: String = "";
  type: String = "";
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

</script>

<template>
    
  <div>
    <div v-if="data.length > 0">
      <div v-for="(item, index) in data" :key="index">

        <div style="border: 3px solid black; height: auto;">
          <label style="font-size: 30px; font-weight: bold; padding-left: 30px;">{{ item.name }}, 
            {{ item.type.toLowerCase() }}</label><br>
          <label style="font-size: 20px; font-weight: bold; padding-left: 30px;">{{ item.description }}</label><br>
        </div>

      </div>
    </div>    
    <div v-else>
      <p style="font-size: 30px;">You dont have saved exercises</p>
    </div>      
  </div>
  

</template>
