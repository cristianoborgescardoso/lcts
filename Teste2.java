package projects.localizationVANT.localization;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import sinalgo.nodes.Position;

public class NodeLocationSet
{

    DecimalFormat formatter = new DecimalFormat("0.00");

    private ArrayList<NodeLocation> referenceList;

    public NodeLocationSet()
    {
        referenceList = new ArrayList<>();
    }

    public NodeLocationSet(List<NodeLocation> newReferenceList)
    {
        referenceList = new ArrayList<>(newReferenceList.size());
        for (NodeLocation nodeLocation : newReferenceList)
        {
            referenceList.add(nodeLocation);
        }
    }

    public boolean add(NodeLocation referencePoint)
    {
        int i;
        boolean isNew = true; // inserido com sucesso

        // System.out.printf("tentando adicionar o no %d na lista\n", refPoint.getId());
        // verificar se jah esta inserido
        /*
         * for (i = 0; i < list.size(); i++){ if (list.get(i).getId() == refPoint.getId()){ result = false; //System.out.printf("NAO ADD\n"); i = list.size(); } }
         */
        // verificar se a posicao jah foi inserida
        for (i = 0; i < referenceList.size(); i++)
        {
            if (referenceList.get(i).getPosicaoPerfeita().getPosition().equals(referencePoint.getPosicaoPerfeita().getPosition()))
            {
                isNew = false;
                break;
            }
            // if (list.get(i).getPosicaoPerfeita().getPosition().xCoord == refPoint.getPosicaoPerfeita().getPosition().xCoord && list.get(i).getPosicaoPerfeita().getPosition().yCoord == refPoint.getPosicaoPerfeita().getPosition().yCoord && list.get(i).getPosicaoPerfeita().getPosition().zCoord ==
            // refPoint.getPosicaoPerfeita().getPosition().zCoord)
            // {
            // result = false;
            // i = list.size();
            // }
        }

        // se nao estiver, insere
        if (isNew == true)
        {
            referenceList.add(referencePoint);
        }

        return isNew;
    }
//
//    public void show()
//    {
//        int i;
//        for (i = 0; i < referenceList.size(); i++)
//        {
//            System.out.println(referenceList.get(i).getPosicaoPerfeita().getPosition().xCoord + " " + referenceList.get(i).getPosicaoPerfeita().getPosition().yCoord + " " + referenceList.get(i).getPosicaoPerfeita().getPosition().zCoord);
//
//        }
//    }

    public String show()
    {
        StringBuilder stringBuilder = new StringBuilder();
        int i;
        for (i = 0; i < referenceList.size(); i++)
        {
            stringBuilder.append(referenceList.get(i).getPosicaoPerfeita().getPosition().xCoord + " " + referenceList.get(i).getPosicaoPerfeita().getPosition().yCoord + " " + referenceList.get(i).getPosicaoPerfeita().getPosition().zCoord + " | " + formatter.format(referenceList.get(i).getDistance()));
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public int size()
    {
        return referenceList.size();
    }

    public void removeUnlikedValue()
    {
        ArrayList<NodeLocation> referenceListWithLikedValues = new ArrayList<>();

        boolean allowed;
        for (NodeLocation nodeLocation : referenceList)
        {
            allowed = true;
            for (NodeLocation nodeLocationCopy : referenceListWithLikedValues)
            {
                if (Math.abs(nodeLocation.getPosicaoPerfeita().getPosition().zCoord - nodeLocationCopy.getPosicaoPerfeita().getPosition().zCoord) < 2)
                {
                    allowed = false;
                }
            }
            if (allowed)
            {
                referenceListWithLikedValues.add(nodeLocation);
            }
        }
        referenceList = referenceListWithLikedValues;
    }

    public NodeLocation get(int index)
    {
        return referenceList.get(index);
    }

    public double residual(LPoint point)
    {
        double resSum = 0.0;
        int i;

        for (i = 0; i < referenceList.size(); i++)
        {
            // System.out.println("i = " + i);
            // System.out.printf("distance = %f \n", list.get(i).getDistance() );
            // System.out.printf("distance2 = %f \n", list.get(i).getPosCalc().distance(point) );
            // System.out.printf("");
            resSum += Math.abs(referenceList.get(i).getDistance() - referenceList.get(i).getPosCalc().distance(point));
        }

        return resSum / referenceList.size();
    }

    public String allResiduals(LPoint point)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (NodeLocation nodeLocation : referenceList)
        {
//			stringBuilder.append(String.format("nodeLocation.getDistance()='%s'|nodeLocation.getPosCalc().distance(point)='%s'|a-b'%s''", formatter.format(nodeLocation.getDistance()), formatter.format(nodeLocation.getPosCalc().distance(point)), formatter.format(Math.abs(nodeLocation.getDistance() - nodeLocation.getPosCalc().distance(point)))));
            stringBuilder.append("nodeLocation.getDistance()='");
            stringBuilder.append(formatter.format(nodeLocation.getDistance()));
            stringBuilder.append("'|");
            stringBuilder.append("nodeLocation.getPosCalc().distance(point)='");
            stringBuilder.append(formatter.format(nodeLocation.getPosCalc().distance(point)));
            stringBuilder.append("'|");
            stringBuilder.append("a-b='");
            stringBuilder.append(formatter.format(Math.abs(nodeLocation.getDistance() - nodeLocation.getPosCalc().distance(point))));
            stringBuilder.append("'");
            stringBuilder.append(",\n");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String getReferencePointsId()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (NodeLocation nodeLocation : referenceList)
        {
            stringBuilder.append(String.valueOf(nodeLocation.getId()));
            stringBuilder.append(",");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public String getCoordenadas()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("x");
        stringBuilder.append("	");
        stringBuilder.append("y");
        stringBuilder.append("	");
        stringBuilder.append("z");
        stringBuilder.append("	");
        stringBuilder.append("\n");
        for (NodeLocation nodeLocation : referenceList)
        {
            stringBuilder.append(formatter.format(nodeLocation.getPosCalc().getPosition().xCoord));
            stringBuilder.append("	");
            stringBuilder.append(formatter.format(nodeLocation.getPosCalc().getPosition().yCoord));
            stringBuilder.append("	");
            stringBuilder.append(formatter.format(nodeLocation.getPosCalc().getPosition().zCoord));
            stringBuilder.append("	");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public List<NodeLocation> getReferenceList()
    {
        return referenceList;
    }
    public void sortReferenceListByResidual()
    {
        Collections.sort(referenceList);
    }
    public void sortReferenceListByResidualReverseOrder()
    {
        Collections.sort(referenceList,Collections.reverseOrder());
    }
    
    public void sortReferenceListByRssiResidual(Position position)
    {
        updateAllRssiResidual(position);                
        referenceList.sort(Comparator.comparingDouble((NodeLocation a) -> a.getRssiResidual()));
    }
    public void sortReferenceListByRssiResidualReverseOrder(Position position)
    {
        updateAllRssiResidual(position);
        referenceList.sort(Comparator.comparingDouble((NodeLocation a) -> a.getRssiResidual()).reversed());
    }
    
    public void updateAllRssiResidual(Position position)
    {
        referenceList.stream().forEach((nodeLocation) ->
        {
            nodeLocation.setRssiResidual(position);
        });       
    }
}
