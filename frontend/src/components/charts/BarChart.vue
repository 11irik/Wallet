<script>
    import {Bar} from "vue-chartjs";
    import {mapState} from "vuex";

    export default {
        extends: Bar,

        computed: {
            ...mapState({
                tagsStats: state => state.tagsStats.tagsStats,
            }),
        },


        mounted() {
            this.renderChart(
                {
                    labels: this.tagsStats[0],
                    datasets: [
                        {
                            label: "Суммы по меткам",
                            backgroundColor: "#f87979",
                            data: this.tagsStats[1]
                        }
                    ]
                },
                {responsive: true, maintainAspectRatio: false}
            );
        }
    };
</script>
