import {NextApiRequest, NextApiResponse} from "next";
import axios from "axios";

const apiBlockingInstance = axios.create({
    baseURL: process.env.host,
    timeout: 100000,
    responseType: 'json'
})

export default async function chatHandler(req: NextApiRequest, res: NextApiResponse) {
    const message = req.body;
    const response = await apiBlockingInstance.post('/chat', message);
    res.status(200).json(response.data);
}