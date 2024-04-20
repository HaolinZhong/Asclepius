import axios from "axios";
import {NextApiRequest, NextApiResponse} from "next";

const chatApiStreamInstance = axios.create({
    baseURL: process.env.api,
    timeout: 100000,
    responseType: 'stream'
})

const chatApiBlockingInstance = axios.create({
    baseURL: process.env.api,
    timeout: 100000,
    responseType: 'json'
})


export default async function chatHandler(req: NextApiRequest, res: NextApiResponse) {
    const message = req.body;
    const response = await chatApiBlockingInstance.post('/chat', message);
    res.status(200).json(response.data);
}