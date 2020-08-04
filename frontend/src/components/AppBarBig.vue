<template>
    <div>
        <NavigationDrawer v-model="drawer"></NavigationDrawer>

        <v-app-bar v-if="profile"
                   app
                   color="indigo"
                   dark
                   class="hidden-xs-and-down"
        >
            <v-app-bar-nav-icon v-if="profile" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

            <v-btn text
                   v-if="profile"
                   @click="showHome">
                {{defaultAccount.name}}
            </v-btn>

            <v-btn text
                   v-if="profile"
                   @click="showVaults">
                Финансы
            </v-btn>

            <v-btn text
                   v-if="profile"
                   @click="showTransactions">
                Транзакции
            </v-btn>

            <v-btn text
                   v-if="profile"
                   @click="showStats">
                Статистика
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn text
                   v-if="profile"
                   @click="showProfile">
                {{profile.name}}
            </v-btn>

            <v-btn v-if="profile" icon @click="exit">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
    </div>
</template>

<script>
    import {mapState} from "vuex";
    import NavigationDrawer from "./NavigationDrawer.vue";

    export default {
        name: "AppBarBig",

        components: {
            NavigationDrawer,
        },

        data: () => ({
            drawer: false,
        }),

        computed: {
            ...mapState({
                defaultAccount: state => state.defaultAccount.defaultAccount,
                accounts: state => state.accounts.accounts,
                profile: state => state.profile.profile,
            }),
        },

        methods: {
            showVaults() {
                this.$router.push('/vaults')
            },

            showStats() {
                this.$router.push('/stats')
            },

            showHome() {
                this.$router.push('/')
            },

            showProfile() {
                this.$router.push('/profile')
            },

            showTransactions() {
                this.$router.push('/transactions')
            },

            showAuth() {
                this.$router.push('/auth')
            },

            exit() {
                //todo
                // this.$store.dispatch('cleanProfileAction')
                this.$router.push('/logout')
            },
        },

        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        },
    }
</script>

<style scoped>

</style>