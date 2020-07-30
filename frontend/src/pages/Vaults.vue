<template>
    <v-container>
        <VaultList></VaultList>
        <VaultDialog v-model="dialog"></VaultDialog>
    </v-container>
</template>

<script>
    import VaultDialog from "../components/dialogs/VaultDialog.vue";
    import VaultList from "../components/lists/VaultList.vue";

    export default {
        name: "Vaults",
        components: {VaultDialog, VaultList},

        data() {
            return {
                dialog: false,
            }
        }
    }
</script>

<style scoped>

</style>