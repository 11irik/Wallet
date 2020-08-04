<script>
    import {Line} from "vue-chartjs";

    export default {
        extends: Line,

        props: {
            label: String,
            data: Array
        },

        mounted() {
            this.renderChart(
                {
                    labels: this.data[0],
                    datasets: [
                        {
                            label: this.label,
                            data: this.data[1],
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