import axios from "axios";
import {ChatMessage, ChatMessageRoleEnum} from "@/app/lib/entity/chat-message";
import {NextApiRequest, NextApiResponse} from "next";

const chatApiInstance = axios.create({
    baseURL: process.env.api,
    timeout: 100000,
    responseType: 'stream'
})


export async function streamingMessageHandler(
    req: NextApiRequest,
    res: NextApiResponse
) {
    const {requestMessage, updateMessage} = req.body
    try {
        await chatApiInstance.post('/chat', requestMessage, {
            onDownloadProgress: progressEvent => {
                console.log(progressEvent)
                updateMessage(progressEvent.event.currentTarget.responseText)
            }
        });
        res.status(200)
    } catch (err) {
        res.status(500).send({ error: 'Failed to fetch data' })
    }
}

export default async function streamingMessage(requestMessage: ChatMessage, updateMessage) {

    await chatApiInstance.post('/chat', requestMessage, {
        onDownloadProgress: progressEvent => {
            console.log(progressEvent)
            updateMessage(progressEvent.event.currentTarget.responseText)
        }
    });
}