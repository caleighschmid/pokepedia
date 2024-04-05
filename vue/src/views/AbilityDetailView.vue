<template>
    <div>
        <ability-detail :id="$route.params.id" />
    </div>
</template>

<script>
import AbilityDetail from '../components/AbilityDetail.vue';
export default {
    components: { AbilityDetail },
  };
</script>

<style>
</style>