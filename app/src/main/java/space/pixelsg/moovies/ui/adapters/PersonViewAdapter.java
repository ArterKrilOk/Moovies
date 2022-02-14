package space.pixelsg.moovies.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import space.pixelsg.moovies.R;
import space.pixelsg.moovies.data.models.Person;
import space.pixelsg.moovies.databinding.PersonListItemBinding;

public class PersonViewAdapter extends RecyclerView.Adapter<PersonViewAdapter.PersonViewHolder> {
    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        private final PersonListItemBinding binding;

        public PersonViewHolder(PersonListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setPerson(Person person) {
            binding.posterView.loadImage(person.profilePath);
            binding.textView.setText(person.name);
        }
    }

    public final List<Person> persons;

    public PersonViewAdapter(List<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PersonListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.person_list_item, parent, false);
        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.setPerson(persons.get(position));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
