package org.jenkinsci.plugins.customizebuildnow;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import jenkins.model.OptionalJobProperty;
import jenkins.model.ParameterizedJobMixIn;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest2;

/**
 * Allow jobs to provide alternate label to "Build Now"
 */
public class BuildNowTextProperty extends OptionalJobProperty<Job<?, ?>> {
    private transient String alternateBuildNow;

    private transient String alternateBuildWithParams;

    private transient String alternateBuildButton;

    private Labels labels;

    @DataBoundConstructor
    public BuildNowTextProperty(Labels labels) {
        this.labels = labels;
    }

    /**
     * @param alternateBuildNow new build now text
     * @deprecated use {@link BuildNowTextProperty(Labels)}
     */
    @Deprecated
    public BuildNowTextProperty(String alternateBuildNow) {
        labels = new Labels();
        labels.setAlternateBuildNow(alternateBuildNow);
    }

    public Object readResolve() {
        if (labels == null) {
            labels = new Labels();
            labels.setAlternateBuildButton(alternateBuildButton);
            labels.setAlternateBuildNow(alternateBuildNow);
            labels.setAlternateBuildWithParams(alternateBuildWithParams);
        }
        return this;
    }

    public Labels getLabels() {
        return labels;
    }

    @Extension
    @Symbol("customizeBuildNow")
    public static final class DescriptorImpl extends OptionalJobPropertyDescriptor {

        public DescriptorImpl() {
            super(BuildNowTextProperty.class);
        }

        @Override
        public JobProperty<?> newInstance(StaplerRequest2 req, JSONObject formData) throws FormException {
            return formData.optBoolean("specified") ? super.newInstance(req, formData) : null;
        }

        @Override
        public boolean isApplicable(Class<? extends Job> jobType) {
            return ParameterizedJobMixIn.ParameterizedJob.class.isAssignableFrom(jobType);
        }

        @Override
        @NonNull
        public String getDisplayName() {
            return "Provide alternate Labels for Build Links and Buttons";
        }
    }
}
