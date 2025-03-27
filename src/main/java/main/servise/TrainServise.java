package main.servise;

import main.logic.Train;

import java.util.*;

public class TrainServise {
    public List<Train> findTrainByPointOfDestination(List<Train> trains,String pointOfDestination){
        List<Train> result = new ArrayList<>();
        for (Train train : trains) {
            if (train.getPointOfDestination().equals(pointOfDestination)) {
                result.add(train);
            }
        }
        return result;
    }
    public List<Train> findTrainByDepartureTime(List<Train> trains,String departureTime){
        List<Train> result = new ArrayList<>();
        for (Train train : trains) {
            double temp1;
            if(train.getDepartureTime().length()==5){
                temp1=Double.parseDouble(train.getDepartureTime().substring(0,2))+
                        (double) Integer.parseInt(train.getDepartureTime().substring(3, 5)) /60;
            }else {
                temp1=Double.parseDouble(train.getDepartureTime().substring(0,1))+
                        (double) Integer.parseInt(train.getDepartureTime().substring(2, 4)) /60;
            }
            double temp2;
            if(departureTime.length()==5){
                temp2=Double.parseDouble(departureTime.substring(0,2))+
                        (double) Integer.parseInt(departureTime.substring(3, 5)) /60;
            }else {
                temp2=Double.parseDouble(departureTime.substring(0,1))+
                        (double) Integer.parseInt(departureTime.substring(2, 4)) /60;
            }
            if (temp1>temp2) {
                result.add(train);
            }
        }
        return result;
    }
    public List<Train> findTrainByPointOfDestinationAndNumberOfSeats
            (List<Train> trains,String pointOfDestination,int n){
        List<Train> result = new ArrayList<>();
        for (Train train : trains) {
            if (train.getPointOfDestination().equals(pointOfDestination)
                    && train.getNumberOfSeats()>=n) {
                result.add(train);
            }
        }
        return result;
    }

    public List<Train> sortTrainsByNumberOfIntermediateStopsAndTrainNumber(List<Train> trains) {
        trains.sort(Comparator.comparing(Train::getNumberOfIntermediateStops)
                .thenComparing(Train::getTrainNumber));
        return trains;
    }

    public Train findTrainByTrainNumberAndNumberOfIntermediateStops(List<Train> trains, int id, int numberOfIntermediateStops) {
        for (Train train : trains) {
            if (train.getId()==id) {
                if (train.getNumberOfIntermediateStops()==numberOfIntermediateStops) {
                    return train;
                }
            }
        }
        return null;
    }
    public Map<String, List<Train>>
    createMapWithKeyPointOfDestinationAndValueListOfTrainsSortedByTrainNumber(List<Train> trains) {
        Map<String, List<Train>> map = new HashMap<>();
        for (Train train : trains) {
            String pointOfDestination = train.getPointOfDestination();
            if (!map.containsKey(pointOfDestination)) {
                map.put(pointOfDestination, new ArrayList<>());
            }
            List<Train> list = map.get(pointOfDestination);
            list.add(train);
            list.sort(Comparator.comparing(Train::getTrainNumber));
            map.replace(pointOfDestination,list);
        }
        return map;
    }
    public List<Train>
    findTrainsWithLessNumberOfIntermediateStopsByPointOfDestination(List<Train> trains) {
        List<Train> resultTrains = new ArrayList<>();
        Map<String, List<Train>> map =
                createMapWithKeyPointOfDestinationAndValueListOfTrainsSortedByTrainNumber(trains);
        for(Map.Entry<String, List<Train>> entry : map.entrySet()) {
            entry.getValue().sort(Comparator.comparing(Train::getNumberOfIntermediateStops));
        }
        for (Map.Entry<String, List<Train>> entry : map.entrySet()) {
            resultTrains.add(entry.getValue().getFirst());
        }
        return resultTrains;
    }

    public List<Train> findTrainsById(List<Train> trains, int id){
        List<Train> result = new ArrayList<>();
        for (Train train : trains) {
            if (train.getId()==id){
                result.add(train);
            }
        }
        return result;
    }

    public List<Train> findTrainsByPointOfDestination
            (List<Train> trains, String PointOfDestination) {
        List<Train> resultTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getPointOfDestination().equals(PointOfDestination)) {
                resultTrains.add(train);
            }
        }
        return  resultTrains;
    }
    public  List<Train> findTrainsByTrainNumber(List<Train> trains, long trainNumber){
        List<Train> resultTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getTrainNumber()==trainNumber) {
                resultTrains.add(train);
            }
        }
        return  resultTrains;
    }
    public  List<Train> findTrainsByDepartureTime(List<Train> trains,String departureTime){
        List<Train> result = new ArrayList<>();
        for (Train train : trains) {
            if (train.getDepartureTime().equals(departureTime)) {
                result.add(train);
            }
        }
        return result;
    }
    public List<Train> findTrainsByNumberOfSeats(List<Train> trains, int numberOfSeats){
        List<Train> resultTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getNumberOfSeats()==numberOfSeats) {
                resultTrains.add(train);
            }
        }
        return  resultTrains;
    }
    public List<Train> findTrainsByTrainsTravelTime(List<Train> trains,String trainsTravelTime){
        List<Train> resultTrains = new ArrayList<>();
        for (Train train : trains) {
            if(train.getTravelTime().equals(trainsTravelTime)) {
                resultTrains.add(train);
            }
        }
        return  resultTrains;
    }
    public List<Train> findTrainsByNumberOfIntermediateStops
            (List<Train> trains, int numberOfIntermediateStops) {
        List<Train> resultTrains = new ArrayList<>();
        for (Train train : trains) {
            if (train.getNumberOfIntermediateStops()==numberOfIntermediateStops) {
                resultTrains.add(train);
            }
        }
        return  resultTrains;
    }

    public String showTrains(List<Train>  trains) {
        StringBuilder resultTrains = new StringBuilder();
        for (Train train : trains) {
            resultTrains.append(train.ShowToString());
        }
        return resultTrains.toString();
    }

    public String showTrainsFields() {
        return "id  point of destination  train number  departure time" +
                "  number of seats  travel time  number of intermediate stops";
    }

    public boolean deleteTrainById(List<Train> trains, int id) {
        boolean result = false;
        for (Train train : trains) {
            if (train.getId()==id) {
                trains.remove(train);
                result=true;
                break;
            }
        }
        return result;
    }
}
