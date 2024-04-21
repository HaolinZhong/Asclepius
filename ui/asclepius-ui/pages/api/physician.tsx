import {NextApiRequest, NextApiResponse} from "next";
import axios from "axios";

const apiBlockingInstance = axios.create({
    baseURL: process.env.NEXT_PUBLIC_host,
    timeout: 100000,
    responseType: 'json'
})

export default async function physicianHandler(req: NextApiRequest, res: NextApiResponse) {
    const response = await apiBlockingInstance.get('/physician');
    res.status(200).json(response.data);
}