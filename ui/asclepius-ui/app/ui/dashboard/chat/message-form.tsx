'use client'

import { useState } from "react";
import { useMessage } from "@/app/ui/dashboard/chat/chat-context";
import { Button, TextArea } from '@apideck/components'
import {PaperAirplaneIcon} from "@heroicons/react/24/outline";
import {ButtonBase} from "@mui/material";

export default function MessageForm() {
    const [content, setContent] = useState('')
    const { addMessage, isStreaming } = useMessage();

    const handleSubmit = async (e) => {
        e?.preventDefault()
        if (content.trim() !== '') {
            addMessage(content)
        }
        setContent('')
    }

    return (
        <form
            className="relative mx-auto max-w-3xl rounded-t-xl"
            onSubmit={handleSubmit}
        >
            <div className="h-[130px] rounded-t-xl border border-gray-300 bg-white p-5 shadow-sm">
                <label htmlFor="content" className="sr-only">
                    Your message
                </label>
                <TextArea
                    name="content"
                    placeholder="Enter your message here..."
                    rows={3}
                    value={content}
                    autoFocus
                    className="w-full border border-gray-300 p-3 text-gray-900 focus:outline-none focus:ring-2 focus:ring-gray-500"
                    onChange={(e) => setContent(e.target.value)}
                />
                <div className="absolute right-8 bottom-10">
                    <ButtonBase className="rounded-md shadow-lg outline" type="submit" size="small">
                        <PaperAirplaneIcon className="w-6 rounded-md"/>
                    </ButtonBase>
                </div>
            </div>
        </form>
    )
}
