<script>
    import {Line} from "vue-chartjs";
    import {mapState} from "vuex";

    export default {
        extends: Line,

        computed: {
            ...mapState({
                daysStats: state => state.daysStats.daysStats,
            }),
        },

        mounted() {
            this.renderChart(
                {
                    labels: this.daysStats[0],
                    datasets: [
                        {
                            label: "Сумма в день",
                            data: this.daysStats[1],
                            backgroundColor: "transparent",
                            borderColor: "rgba(1, 116, 188, 0.50)",
                            pointBackgroundColor: "rgba(171, 71, 188, 1)"
                        }
                    ]
                },
                {
                    responsive: true,
                    maintainAspectRatio: false,
                    title: {
                        display: true,
                    }
                }
            );
        }
    };
</script>