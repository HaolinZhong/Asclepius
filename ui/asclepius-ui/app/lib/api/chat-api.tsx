import axios from "axios";
import {ChatMessage, ChatMessageRoleEnum} from "@/app/lib/entity/chat-message";

const chatApiInstance = axios.create({
    baseURL: process.env.api,
    timeout: 100000,
    responseType: 'stream'
})

export default async function streamingMessage(requestMessage: ChatMessage, updateMessage) {

    await chatApiInstance.post('', requestMessage, {
        onDownloadProgress: progressEvent => {
            updateMessage(progressEvent.event.currentTarget.responseText)
        }
    });
}