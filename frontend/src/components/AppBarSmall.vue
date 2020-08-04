<template>
    <div>
        <NavigationDrawer v-model="drawer"></NavigationDrawer>

        <v-app-bar v-if="profile"
                   app
                   color="indigo"
                   dark
                   class="hidden-sm-and-up"
        >
            <v-app-bar-nav-icon v-if="profile" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>

            <v-btn text
                   v-if="profile"
                   @click="showHome">
                <v-icon>mdi-bank</v-icon>
            </v-btn>

            <v-btn text
                   v-if="profile"
                   @click="showVaults">
                <v-icon>mdi-cash-multiple</v-icon>
            </v-btn>

            <v-btn text
                   v-if="profile"
                   @click="showTransactions">
                <v-icon>mdi-clipboard-arrow-left</v-icon>
            </v-btn>

            <v-btn text
                   v-if="profile"
                   @click="showStats">
                <v-icon>mdi-chart-arc</v-icon>
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn text
                   v-if="profile"
                   @click="showProfile">
                <v-icon>mdi-account</v-icon>
            </v-btn>

            <v-btn v-if="profile" icon @click="exit">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
    </div>
</template>

<script>
    import {mapState} from 'vuex';
    import NavigationDrawer from './NavigationDrawer.vue';

    export default {
        name: 'AppBarSmall',

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
                this.$router.push('/vaults');
            },

            showStats() {
                this.$router.push('/stats');
            },

            showHome() {
                this.$router.push('/');
            },

            showProfile() {
                this.$router.push('/profile');
            },

            showTransactions() {
                this.$router.push('/transactions');
            },

            showAuth() {
                this.$router.push('/auth');
            },

            exit() {
                this.$store.dispatch('cleanProfileAction');
                this.$router.push('/logout');
            },

            selectAccount(account) {
                this.$store.dispatch('retrieveCategoryAction', account);
                this.drawer = false;
            },
        },

        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth');
            }
        },
    }
</script>

<style scoped>

</style>
