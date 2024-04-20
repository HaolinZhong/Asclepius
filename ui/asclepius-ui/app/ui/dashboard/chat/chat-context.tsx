'use client'

import {createContext, ReactNode, useContext, useEffect, useState} from "react";
import {ChatMessage, ChatMessageRoleEnum} from "@/app/lib/entity/chat-message";

export interface ContextProps {
    messages: ChatMessage[]
    addMessage: (content: string) => Promise<void>
    isStreaming
}

export const ChatContext = createContext<Partial<ContextProps>>({});

const sendMessage = async (message: ChatMessage) => {
    try {
        const response = await fetch("/api/chat", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(message)
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
    }
}

export const ChatContextProvider = ({ children }: { children: ReactNode }) => {
    const [messages, setMessages] = useState<ChatMessage[]>([]);
    const [isStreaming, setIsStreaming] = useState<boolean>(false);

    useEffect(() => {
        const initializeChat = () => {
            const welcomeMessage: ChatMessage = {
                role: ChatMessageRoleEnum.Assistant,
                content: `Hi, I'm  Asclepius, your virtual clinic receptionist. Please describe your symptom and I will help you to find 
                 a suitable physician and make an appointment for you.`,
            }
            setMessages([welcomeMessage])
        }

        // When no messages are present, we initialize the chat the system message and the welcome message
        // We hide the system message from the user in the UI
        if (!messages?.length) {
            initializeChat()
        }
    }, [messages?.length, setMessages])

    const addMessage = async (content: string) => {
        try {
            const requestMessage: ChatMessage = {
                role: ChatMessageRoleEnum.User,
                author: 'user',
                content: content
            };
            setMessages([...messages, requestMessage]);
            const responseContent = await sendMessage(requestMessage)
            const responseMessage: ChatMessage = {
                role: ChatMessageRoleEnum.Assistant,
                author: ChatMessageRoleEnum.Assistant,
                content: responseContent
            }
            setMessages([...messages, requestMessage, responseMessage])
            setIsStreaming(true);
        } catch (error) {
            // addToToast
        } finally {
            setIsStreaming(false);
        }
    }

    return (
        <ChatContext.Provider value={{messages, addMessage, isStreaming}}>
            {children}
        </ChatContext.Provider>
    )
}

export const useMessage: Function = () => {
    return useContext(ChatContext) as ContextProps;
}