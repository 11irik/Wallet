<template>
    <v-app>
        <AppBarBig></AppBarBig>
        <AppBarSmall></AppBarSmall>

        <v-main>
            <router-view></router-view>
        </v-main>
    </v-app>
</template>

<script>
    import NavigationDrawer from './components/NavigationDrawer.vue';
    import AppBarSmall from './components/AppBarSmall.vue';
    import AppBarBig from './components/AppBarBig.vue';

    export default {
        components: {
            NavigationDrawer,
            AppBarSmall,
            AppBarBig
        },
    }
</script>

<style>

</style>
