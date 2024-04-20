import axios from "axios";
import {ChatMessage, ChatMessageRoleEnum} from "@/app/lib/entity/chat-message";
import {NextApiRequest, NextApiResponse} from "next";

const chatApiInstance = axios.create({
    baseURL: process.env.api,
    timeout: 100000,
    responseType: 'stream'
})

export const streamingChat = async (
    req: NextApiRequest,
    res: NextApiResponse
) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Content-Type', 'text/event-stream;charset=utf-8');
    res.setHeader('Cache-Control', 'no-cache, no-transform');
    res.setHeader('X-Accel-Buffering', 'no');
    res.setHeader('Connection', 'keep-alive');

    const {requestMessage} = req.body

    try {
        await chatApiInstance.post('/chat', requestMessage, {
            onDownloadProgress: progressEvent => {
                res.write(progressEvent.event.currentTarget.responseText)
            }
        });
    } catch (err) {
        res.status(500).send({ error: 'Failed to fetch data' })
    } finally {
        res.end()
    }
}