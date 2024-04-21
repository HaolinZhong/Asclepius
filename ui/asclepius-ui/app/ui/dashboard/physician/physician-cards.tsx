'use client'

import {Card} from "@/app/ui/dashboard/physician/card";

const fetchPhysician = async () => {
    const response = await fetch('http://localhost:3000/api/physician')
    const physicians = await response.json()
    return physicians;
}

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