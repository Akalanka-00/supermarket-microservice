import React from 'react'

const LocateOnMap = () => {
  const lat = 53;
  const lon = 32;
  const zoom = 16;
  return (
    <div>
      <iframe src={`https://maps.google.com/maps?q=${lat},${lon}&z=${zoom}&output=embed`}
      width={"600"}
      height={"450"}
      loading='lazy'
      referrerPolicy='no-referrer-when-downgrade'
      title='google map'

      ></iframe>
    </div>
  )
}

export default LocateOnMap
