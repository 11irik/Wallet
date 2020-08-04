<template>
    <v-container>
        <Vaults/>
        <VaultDialogButton/>
    </v-container>
</template>

<script>
    import VaultDialogButton from '../components/VaultDialogButton.vue';
    import Vaults from '../components/Vaults.vue';

    export default {
        name: 'VaultPage',
        components: {VaultDialogButton, Vaults},
    }
</script>

<style scoped>

</style>