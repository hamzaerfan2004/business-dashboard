import {

BarChart,

Bar,

XAxis,

YAxis,

Tooltip,

CartesianGrid,

ResponsiveContainer

} from "recharts";

export default function CategoryBarChart({ data }) {

return (

<ResponsiveContainer
width="100%"
height={350}
>

<BarChart data={data}>

<CartesianGrid strokeDasharray="3 3" />

<XAxis dataKey="category" />

<YAxis />

<Tooltip />

<Bar dataKey="total" />

</BarChart>

</ResponsiveContainer>

);

}