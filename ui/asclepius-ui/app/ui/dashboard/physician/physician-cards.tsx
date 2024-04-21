'use client'

import {Card} from "@/app/ui/dashboard/physician/card";
import {fetchPhysician} from "@/app/lib/fetch";

export default async function PhysicianCards() {

    const physicians = await fetchPhysician();

    return (
        <div className="grid gap-6 sm:grid-cols-1 lg:grid-cols-2">
            {physicians.map((physician, index) => (
                <div key={index} className="w-full p-4">
                    <Card physician={physician} />
                </div>
            ))}
        </div>
    )
}