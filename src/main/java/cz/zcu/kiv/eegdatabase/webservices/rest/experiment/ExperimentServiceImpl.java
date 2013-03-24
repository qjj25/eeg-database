package cz.zcu.kiv.eegdatabase.webservices.rest.experiment;

import cz.zcu.kiv.eegdatabase.data.dao.*;
import cz.zcu.kiv.eegdatabase.data.pojo.*;
import cz.zcu.kiv.eegdatabase.webservices.rest.experiment.wrappers.*;
import cz.zcu.kiv.eegdatabase.webservices.rest.groups.wrappers.ResearchGroupData;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service implementation for Experiment data type.
 * Used in REST service.
 *
 * @author Petr Miko
 */
@Service
public class ExperimentServiceImpl implements ExperimentService {

    private final Comparator<ExperimentData> idComparator = new Comparator<ExperimentData>() {
        @Override
        public int compare(ExperimentData id1, ExperimentData id2) {
            return id1.getExperimentId() - id2.getExperimentId();
        }
    };
    @Autowired
    @Qualifier("personDao")
    private PersonDao personDao;
    @Autowired
    @Qualifier("experimentDao")
    private ExperimentDao experimentDao;
    @Autowired
    private SimpleArtifactDao artifactDao;
    @Autowired
    @Qualifier("digitizationDao")
    private DigitizationDao digitizationDao;
    @Autowired
    private SimpleDiseaseDao diseaseDao;
    @Autowired
    @Qualifier("hardwareDao")
    private HardwareDao hardwareDao;
    @Autowired
    private SimpleSoftwareDao softwareDao;
    @Autowired
    @Qualifier("weatherDao")
    private WeatherDao weatherDao;
    @Autowired
    private SimplePharmaceuticalDao pharmaceuticalDao;
    @Autowired
    private SimpleElectrodeTypeDao electrodeTypeDao;
    @Autowired
    private SimpleElectrodeLocationDao electrodeLocationDao;
    @Autowired
    private SimpleElectrodeFixDao electrodeFixDao;
    @Autowired
    private SimpleElectrodeSystemDao electrodeSystemDao;
    @Autowired
    @Qualifier("experimentDao")
    private GenericDao<ElectrodeConf, Integer> electrodeConfDao;

    @Override
    @Transactional(readOnly = true)
    public List<ExperimentData> getPublicExperiments(int from, int max) {
        return fillAndSortExperiments(experimentDao.getVisibleExperiments(personDao.getLoggedPerson().getPersonId(), from, max));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperimentData> getMyExperiments() {
        Person loggedUser = personDao.getLoggedPerson();
        return fillAndSortExperiments(loggedUser.getExperimentsForOwnerId());
    }

    @Override
    @Transactional(readOnly = true)
    public int getPublicExperimentsCount() {
        return experimentDao.getVisibleExperimentsCount(personDao.getLoggedPerson().getPersonId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArtifactData> getArtifacts() {
        List<ArtifactData> artifacts = new ArrayList<ArtifactData>();
        List<Artifact> arts = artifactDao.getAllRecords();

        for (Artifact art : arts) {
            ArtifactData artifact = new ArtifactData();
            artifact.setArtifactId(art.getArtifactId());
            artifact.setCompensation(art.getCompensation());
            artifact.setRejectCondition(art.getRejectCondition());
            artifacts.add(artifact);
        }

        return artifacts;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DigitizationData> getDigitizations() {
        List<DigitizationData> digitizations = new ArrayList<DigitizationData>();
        Collection<Digitization> digis = digitizationDao.getAllRecords();

        for (Digitization digi : digis) {
            DigitizationData digitization = new DigitizationData();
            digitization.setDigitizationId(digi.getDigitizationId());
            digitization.setSamplingRate(digi.getSamplingRate());
            digitization.setFilter(digi.getFilter());
            digitization.setGain(digi.getGain());
            digitizations.add(digitization);
        }

        return digitizations;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiseaseData> getDiseases() {
        List<DiseaseData> diseases = new ArrayList<DiseaseData>();
        Collection<Disease> disList = diseaseDao.getAllRecords();

        for (Disease dis : disList) {
            DiseaseData disease = new DiseaseData();
            disease.setDiseaseId(dis.getDiseaseId());
            disease.setName(dis.getTitle());
            disease.setDescription(dis.getDescription());
            diseases.add(disease);
        }
        return diseases;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeatherData> getWeatherList() {
        List<WeatherData> weathers = new ArrayList<WeatherData>();
        Collection<Weather> weatherList = weatherDao.getAllRecords();

        for (Weather w : weatherList) {
            WeatherData weather = new WeatherData();
            weather.setWeatherId(w.getWeatherId());
            weather.setTitle(w.getTitle());
            weather.setDescription(w.getDescription());
            weathers.add(weather);
        }

        return weathers;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HardwareData> getHardwareList() {
        List<HardwareData> hardwareList = new ArrayList<HardwareData>();
        Collection<Hardware> hws = hardwareDao.getAllRecords();

        for (Hardware hw : hws) {
            HardwareData hardware = new HardwareData();
            hardware.setHardwareId(hw.getHardwareId());
            hardware.setDefaultNumber(hw.getDefaultNumber());
            hardware.setTitle(hw.getTitle());
            hardware.setType(hw.getType());

            hardwareList.add(hardware);
        }

        return hardwareList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SoftwareData> getSoftwareList() {
        List<SoftwareData> softwareList = new ArrayList<SoftwareData>();
        Collection<Software> sws = softwareDao.getAllRecords();

        for (Software sw : sws) {
            SoftwareData software = new SoftwareData();
            software.setId(sw.getSoftwareId());
            software.setDefaultNumber(sw.getDefaultNumber());
            software.setTitle(sw.getTitle());
            software.setDescription(sw.getDescription());
            softwareList.add(software);
        }
        return softwareList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PharmaceuticalData> getPharmaceuticals() {
        List<PharmaceuticalData> pharmaceuticals = new ArrayList<PharmaceuticalData>();
        Collection<Pharmaceutical> pharms = pharmaceuticalDao.getAllRecords();

        for (Pharmaceutical ph : pharms) {
            PharmaceuticalData pharmaceutical = new PharmaceuticalData();
            pharmaceutical.setId(ph.getPharmaceuticalId());
            pharmaceutical.setTitle(ph.getTitle());
            pharmaceutical.setDescription(ph.getDescription());

            pharmaceuticals.add(pharmaceutical);
        }
        return pharmaceuticals;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectrodeSystemData> getElectrodeSystems() {
        List<ElectrodeSystemData> systemList = new ArrayList<ElectrodeSystemData>();
        Collection<ElectrodeSystem> sysList = electrodeSystemDao.getAllRecords();

        for (ElectrodeSystem s : sysList) {
            ElectrodeSystemData system = new ElectrodeSystemData();
            system.setId(s.getElectrodeSystemId());
            system.setDefaultNumber(s.getDefaultNumber());
            system.setTitle(s.getTitle());
            system.setDescription(s.getDescription());

            systemList.add(system);
        }

        return systemList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectrodeTypeData> getElectrodeTypes() {
        List<ElectrodeTypeData> electrodeTypes = new ArrayList<ElectrodeTypeData>();
        Collection<ElectrodeType> els = electrodeTypeDao.getAllRecords();

        for (ElectrodeType el : els) {
            ElectrodeTypeData electrodeType = new ElectrodeTypeData();
            electrodeType.setId(el.getElectrodeTypeId());
            electrodeType.setDefaultNumber(el.getDefaultNumber());
            electrodeType.setTitle(el.getTitle());
            electrodeType.setDescription(el.getDescription());
            electrodeTypes.add(electrodeType);
        }

        return electrodeTypes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectrodeFixData> getElectrodeFixList() {
        List<ElectrodeFixData> fixList = new ArrayList<ElectrodeFixData>();
        Collection<ElectrodeFix> fixes = electrodeFixDao.getAllRecords();

        for (ElectrodeFix f : fixes) {
            ElectrodeFixData fix = new ElectrodeFixData();
            fix.setId(f.getElectrodeFixId());
            fix.setTitle(f.getTitle());
            fix.setDefaultNumber(f.getDefaultNumber());
            fix.setDescription(f.getDescription());
            fixList.add(fix);
        }
        return fixList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ElectrodeLocationData> getElectrodeLocations() {
        List<ElectrodeLocationData> electrodeLocations = new ArrayList<ElectrodeLocationData>();
        Collection<ElectrodeLocation> locations = electrodeLocationDao.getAllRecords();

        for (ElectrodeLocation el : locations) {
            ElectrodeLocationData location = new ElectrodeLocationData();
            location.setId(el.getElectrodeLocationId());
            location.setDefaultNumber(el.getDefaultNumber());
            location.setAbbr(el.getShortcut());
            location.setTitle(el.getTitle());
            location.setDescription(el.getDescription());

            ElectrodeTypeData type = new ElectrodeTypeData();
            ElectrodeType t = el.getElectrodeType();
            type.setId(t.getElectrodeTypeId());
            type.setDefaultNumber(t.getDefaultNumber());
            type.setTitle(t.getTitle());
            type.setDescription(t.getDescription());

            ElectrodeFixData fix = new ElectrodeFixData();
            ElectrodeFix f = el.getElectrodeFix();
            fix.setId(f.getElectrodeFixId());
            fix.setDefaultNumber(f.getDefaultNumber());
            fix.setTitle(f.getTitle());
            fix.setDescription(f.getDescription());

            location.setElectrodeType(type);
            location.setElectrodeFix(fix);

            electrodeLocations.add(location);
        }

        return electrodeLocations;
    }

    private List<ExperimentData> fillAndSortExperiments(Collection<Experiment> exps) {
        List<ExperimentData> experiments = new ArrayList<ExperimentData>(exps.size());

        for (Experiment exp : exps) {
            ExperimentData expData = new ExperimentData();
            expData.setExperimentId(exp.getExperimentId());
            expData.setStartTime(exp.getStartTime());
            expData.setEndTime(exp.getEndTime());

            ScenarioSimpleData scenarioData = new ScenarioSimpleData();
            scenarioData.setScenarioId(exp.getScenario().getScenarioId());
            scenarioData.setScenarioName(exp.getScenario().getTitle());

            WeatherData weatherData = new WeatherData();
            Weather weather = exp.getWeather();
            weatherData.setWeatherId(weather.getWeatherId());
            weatherData.setTitle(weather.getTitle());
            weatherData.setDescription(weather.getDescription());

            Person subject = exp.getPersonBySubjectPersonId();
            SubjectData subjectData = new SubjectData();
            subjectData.setPersonId(subject.getPersonId());
            subjectData.setName(subject.getGivenname());
            subjectData.setSurname(subject.getSurname());
            subjectData.setLeftHanded(subject.getLaterality() == 'L' || subject.getLaterality() == 'l');
            subjectData.setGender(subject.getGender());
            subjectData.setAge(Years.yearsBetween(new LocalDate(subject.getDateOfBirth()), new LocalDate()).getYears());
            subjectData.setMail(subject.getUsername());

            Artifact artifact = exp.getArtifact();
            ArtifactData artifactData = new ArtifactData();
            artifactData.setArtifactId(artifact.getArtifactId());
            artifactData.setCompensation(artifact.getCompensation());
            artifactData.setRejectCondition(artifact.getRejectCondition());

            Set<Disease> diseases = exp.getDiseases();
            List<DiseaseData> diseaseDatas = new ArrayList<DiseaseData>();
            for (Disease dis : diseases) {
                DiseaseData diseaseData = new DiseaseData();
                diseaseData.setDiseaseId(dis.getDiseaseId());
                diseaseData.setName(dis.getTitle());
                diseaseData.setDescription(dis.getDescription());
                diseaseDatas.add(diseaseData);
            }

            Digitization digitization = exp.getDigitization();
            DigitizationData dgData = new DigitizationData();
            dgData.setDigitizationId(digitization.getDigitizationId());
            dgData.setGain(digitization.getGain());
            dgData.setFilter(digitization.getFilter());
            dgData.setSamplingRate(digitization.getSamplingRate());

            Set<Hardware> hardwares = exp.getHardwares();
            List<HardwareData> hardwareDatas = new ArrayList<HardwareData>();
            for (Hardware h : hardwares) {
                HardwareData hw = new HardwareData();
                hw.setHardwareId(h.getHardwareId());
                hw.setTitle(h.getTitle());
                hw.setType(h.getType());
                hw.setDefaultNumber(h.getDefaultNumber());
                hardwareDatas.add(hw);
            }

            ElectrodeConf elConf = exp.getElectrodeConf();
            ElectrodeConfData electrodeConfData = new ElectrodeConfData();
            //set primitive types
            electrodeConfData.setId(elConf.getElectrodeConfId());
            electrodeConfData.setId(elConf.getImpedance());
            //set electrode system
            ElectrodeSystem elSystem = elConf.getElectrodeSystem();
            ElectrodeSystemData electrodeSystemData = new ElectrodeSystemData();
            electrodeSystemData.setId(elSystem.getElectrodeSystemId());
            electrodeSystemData.setDescription(elSystem.getDescription());
            electrodeSystemData.setTitle(elSystem.getTitle());
            electrodeSystemData.setDefaultNumber(elSystem.getDefaultNumber());
            electrodeConfData.setElectrodeSystem(electrodeSystemData);
            //set electrode locations
            Set<ElectrodeLocation> elLocations = elConf.getElectrodeLocations();

            if (elLocations != null && !elLocations.isEmpty()) {
                List<ElectrodeLocationData> electrodeLocations = new ArrayList<ElectrodeLocationData>(elLocations.size());

                for (ElectrodeLocation el : elLocations) {
                    ElectrodeLocationData electrodeLocation = new ElectrodeLocationData();
                    //set primitives
                    electrodeLocation.setId(el.getElectrodeLocationId());
                    electrodeLocation.setTitle(el.getTitle());
                    electrodeLocation.setDescription(el.getDescription());
                    electrodeLocation.setAbbr(el.getShortcut());
                    electrodeLocation.setDefaultNumber(el.getDefaultNumber());
                    //set complex types
                    ElectrodeFix elFix = el.getElectrodeFix();
                    ElectrodeFixData electrodeFix = new ElectrodeFixData();
                    electrodeFix.setId(elFix.getElectrodeFixId());
                    electrodeFix.setTitle(elFix.getTitle());
                    electrodeFix.setDescription(elFix.getDescription());
                    electrodeFix.setDefaultNumber(elFix.getDefaultNumber());
                    electrodeLocation.setElectrodeFix(electrodeFix);

                    ElectrodeType elType = el.getElectrodeType();
                    ElectrodeTypeData electrodeType = new ElectrodeTypeData();
                    electrodeType.setId(elType.getElectrodeTypeId());
                    electrodeType.setTitle(elType.getTitle());
                    electrodeType.setDescription(elType.getDescription());
                    electrodeType.setDefaultNumber(elType.getDefaultNumber());
                    electrodeLocation.setElectrodeType(electrodeType);

                    electrodeLocations.add(electrodeLocation);
                }
                electrodeConfData.setElectrodeLocations(new ElectrodeLocationDataList(electrodeLocations));
            }

            Set<Pharmaceutical> pharms = exp.getPharmaceuticals();
            List<PharmaceuticalData> pharmaceuticals = new ArrayList<PharmaceuticalData>();
            if (pharms != null && !pharms.isEmpty()) {
                for (Pharmaceutical p : pharms) {
                    PharmaceuticalData pharmaceutical = new PharmaceuticalData();
                    pharmaceutical.setId(p.getPharmaceuticalId());
                    pharmaceutical.setTitle(p.getTitle());
                    pharmaceutical.setDescription(p.getDescription());
                    pharmaceuticals.add(pharmaceutical);
                }
            }

            Set<Software> softs = exp.getSoftwares();
            List<SoftwareData> softwareList = new ArrayList<SoftwareData>();
            if (softs != null && !softs.isEmpty()) {
                for (Software s : softs) {
                    SoftwareData software = new SoftwareData();
                    software.setId(s.getSoftwareId());
                    software.setTitle(s.getTitle());
                    software.setDescription(s.getDescription());
                    software.setDefaultNumber(s.getDefaultNumber());
                    softwareList.add(software);
                }
            }

            ResearchGroup rg = exp.getResearchGroup();
            ResearchGroupData researchGroup = new ResearchGroupData();

            researchGroup.setGroupId(rg.getResearchGroupId());
            researchGroup.setGroupName(rg.getTitle());

            Person ow = exp.getPersonByOwnerId();
            OwnerData owner = new OwnerData();
            String mail[] = ow.getUsername().split("@");
            owner.setId(ow.getPersonId());
            owner.setName(ow.getGivenname());
            owner.setSurname(ow.getSurname());
            if (mail.length == 2) {
                owner.setMailUsername(mail[0]);
                owner.setMailDomain(mail[1]);
            }

            expData.setOwner(owner);
            expData.setResearchGroup(researchGroup);
            expData.setPharmaceuticals(new PharmaceuticalDataList(pharmaceuticals));
            expData.setSoftwareList(new SoftwareDataList(softwareList));
            expData.setElectrodeConf(electrodeConfData);
            expData.setHardwareList(new HardwareDataList(hardwareDatas));
            expData.setScenario(scenarioData);
            expData.setArtifact(artifactData);
            expData.setSubject(subjectData);
            expData.setDiseases(new DiseaseDataList(diseaseDatas));
            expData.setDigitization(dgData);
            expData.setWeather(weatherData);
            experiments.add(expData);
        }
        Collections.sort(experiments, idComparator);
        return experiments;
    }
}