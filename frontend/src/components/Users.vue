<template>
    <div>
        <UserDialog :visible="dialogUser" :onClose="handleCloseUserDialog"></UserDialog>

        <v-card max-width="500" class="mx-auto">
            <v-list>
                <v-list-item-title class="headline mb-1">Пользователи</v-list-item-title>

                <UserList :users="defaultAccount.users"/>

                <!--                todo user rules-->
                <v-list-item v-if="profile.id === defaultAccount.owner.id" @click="openUserDialog">
                    <v-list-item-icon>
                        <v-icon>mdi-plus</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title @click="">Пригласить пользователя</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>

        </v-card>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import UserDialog from './dialogs/UserDialog.vue';
    import UserList from './lists/UserList.vue';

    export default {
        name: 'Users',

        components: {
            UserDialog,
            UserList,
        },

        data() {
            return {
                dialogUser: false,
            }
        },

        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
                profile: state => state.profile.profile,
            }),
        },

        methods: {
            openUserDialog() {
                this.dialogUser = true;
            },

            handleCloseUserDialog() {
                this.dialogUser = false;
            }
        }
    }
</script>

<style scoped>

</style>
