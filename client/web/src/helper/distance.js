function calculateDistance(origin, destination) {
    const [lat1, lon1] = origin;
    const [lat2, lon2] = destination;
    const radius = 6371; // Earth's radius in kilometers
  
    const dLat = (lat2 - lat1) * (Math.PI / 180);
    const dLon = (lon2 - lon1) * (Math.PI / 180);
  
    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos((lat1 * Math.PI) / 180) *
        Math.cos((lat2 * Math.PI) / 180) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);
  
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = radius * c;
  
    return distance;
  }
  
  function estimateTravelTime(origin, destination) {
    const baseSpeed = 60;
    const traffic = -0.5 + Math.random();
    const averageSpeed = traffic * baseSpeed + baseSpeed;
    const distanceInKm = calculateDistance(origin, destination);
    const time = distanceInKm / averageSpeed;
    const output = time < 1 ? `${(time * 60).toFixed(0)} minutes` : `${time.toFixed(2)}h`;
  
    return { distanceInKm, averageSpeed, estimatedTime: output };
  }
  


  export const FlashMartLocation = [6.939116403606681, 79.85456813343887]
  export default estimateTravelTime
  