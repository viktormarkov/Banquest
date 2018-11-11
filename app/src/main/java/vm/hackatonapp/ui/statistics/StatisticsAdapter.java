package vm.hackatonapp.ui.statistics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vm.hackatonapp.R;
import vm.hackatonapp.models.Level;


public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.LevelViewHolder> {
    private List<Level> levels = new ArrayList<>();
    private int userLevel;
    private int levelColor;
    private int levelColorFade;
    private int bonusColor;
    private int bonusColorFade;

    StatisticsAdapter(Context context) {
        levelColor = ContextCompat.getColor(context, R.color.statistic_level);
        levelColorFade = ContextCompat.getColor(context, R.color.statistic_level_fade);

        bonusColor = ContextCompat.getColor(context, R.color.statistic_bonus);
        bonusColorFade = ContextCompat.getColor(context, R.color.statistic_bonus_fade);
    }

    public void setLevels(List<Level> levels) {
        this.levels.clear();
        this.levels.addAll(levels);
        notifyDataSetChanged();
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level, parent, false);
        return new LevelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        Level level = getItem(position);

        holder.level.setText(String.format("%d level", level.getLevel()));
        holder.bonus.setText(level.getBonusText());
        holder.root.setEnabled(level.getLevel() == userLevel);

        if (level.getLevel() > userLevel) {
            holder.level.setTextColor(levelColorFade);
            holder.bonus.setTextColor(bonusColorFade);
        } else {
            holder.level.setTextColor(levelColor);
            holder.bonus.setTextColor(bonusColor);
        }
    }

    class LevelViewHolder extends RecyclerView.ViewHolder {
        TextView level;
        TextView bonus;
        View root;

        LevelViewHolder(View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.level);
            bonus = itemView.findViewById(R.id.bonus);
            root = itemView.findViewById(R.id.item);
        }
    }

    private Level getItem(int position) {
        return levels.get(position);
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }
}
