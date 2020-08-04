<template>
    <v-container>
        <VaultList></VaultList>
        <VaultDialogButton></VaultDialogButton>
    </v-container>
</template>

<script>
    import VaultDialogButton from '../components/VaultDialogButton.vue';
    import VaultList from '../components/lists/VaultList.vue';

    export default {
        name: 'Vaults',
        components: {VaultDialogButton, VaultList},
    }
</script>

<style scoped>

</style>