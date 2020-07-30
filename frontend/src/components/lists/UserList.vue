<template>
    <div>

        <UserDialog v-model="dialogUser"></UserDialog>

        <v-card
                max-width="500"
                class="mx-auto"
        >

            <v-list>
                <v-list-item-title class="headline mb-1">Пользователи</v-list-item-title>

                <v-list-item
                        v-for="item in defaultAccount.users"
                        :key="item.id"
                        @click=""
                >
                    <v-list-item-icon>
                        <v-icon v-if="item.id === defaultAccount.owner.id" color="indigo">mdi-star</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title v-text="item.name"></v-list-item-title>
                    </v-list-item-content>

                    <v-list-item-avatar>
                        <v-img :src="item.userpic"></v-img>
                    </v-list-item-avatar>
                </v-list-item>
                <v-list-item v-if="profile.id === defaultAccount.owner.id" @click="openDial">
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
    import {mapState} from "vuex";
    import UserDialog from "../dialogs/UserDialog.vue";

    export default {
        name: "UserList",

        components: {
            UserDialog
        },

        data() {
            return {
                dialogUser: false,
            }
        },

        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
                accounts: state => state.accounts.accounts,
                profile: state => state.profile.profile,
                tags: state => state.tags.tags,
                categories: state => state.categories.categories,
            }),
        },


        methods: {
            openDial() {
                this.dialogUser = true
            },
        }
    }
</script>

<style scoped>

</style>