package converters;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import services.JobService;
import entities.Job;

@FacesConverter("jobConverter")
public class JobConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
        System.out.println("[JobConverter] getAsObject called with value: \"" + value + "\"");
        if (value == null || value.trim().isEmpty()) {
            System.out.println("[JobConverter] blank → returning null");
            return null;
        }
        try {
            Long id = Long.valueOf(value);
            JobService jobService = CDI.current().select(JobService.class).get();
            System.out.println("[JobConverter] looked up jobService = " + jobService);
            Job job = jobService.findById(id);
            System.out.println("[JobConverter] lookup id=" + id + " → job=" + job);
            return job;
        } catch (Exception e) {
            System.out.println("[JobConverter] ERROR: " + e);
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, Object obj) {
        System.out.println("[JobConverter] getAsString called with obj=" + obj);
        if (!(obj instanceof Job)) {
            System.out.println("[JobConverter] not a Job → \"\"");
            return "";
        }
        Job job = (Job) obj;
        Long id = job.getJobId();
        String s = (id != null) ? id.toString() : "";
        System.out.println("[JobConverter] returning \"" + s + "\"");
        return s;
    }
}
